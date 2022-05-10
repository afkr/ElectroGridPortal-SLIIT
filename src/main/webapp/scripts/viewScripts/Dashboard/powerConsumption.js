let selectedPowerConsumptionId = 0;
let powerConsumptionTable;

$(document).ready(function () {
  $("#powerConsumptionModalCloseBtn").click(function () {
    clearPowerConsumptionFields();
    togglePowerConsumptionModal();
  });

  $("#savePowerConsumption").click(function () {
    savePowerConsumptionClickHandler();
  });

  $("#createPowerConsumption").click(function () {
    clearPowerConsumptionFields();
    fetchUsersForPC();
    togglePowerConsumptionModal(true);
  });

  $("#savePC").click(savePowerConsumptionClickHandler);

  $(document).on("click", "#powerConsumptionTable button.edit", function () {
    let row = $(this).parents("tr");
    let data = powerConsumptionTable.row(row).data();
    editPowerConsumptionClickHandler(data);
  });

  $(document).on("click", "#powerConsumptionTable button.remove", function () {
    let row = $(this).parents("tr");
    let data = powerConsumptionTable.row(row).data();
    deletePowerConsumptionClickHandler(data);
  });

  powerConsumptionTable = $("#powerConsumptionTable").DataTable({
    data: [],
    scrollX: true,

    initComplete: function () {
      $(this.api().table().container())
        .find("input")
        .parent()
        .wrap("<form>")
        .parent()
        .attr("autocomplete", "off");
    },
    order: [],
    columns: [
      { data: "username" },
      { data: "units" },
      { data: "mobileNumber" },
      {
        data: null,
        searchable: false,
        orderable: false,
        defaultContent: "",
        render: function (data, type, row) {
          var content = moment(data.billDate).format("DD-MMM-YYYY");
          return content;
        },
      },
      {
        data: null,
        searchable: false,
        orderable: false,
        defaultContent: "",
        render: function (data, type, row) {
          var content = `<div class='btn-row'><button class='dt-save edit'><i class="fas fa-pencil-alt"></i>Edit</button><button class='dt-remove remove'><i class="fas fa-trash-alt"></i>Remove</button></div>`;
          return content;
        },
      },
    ],
  });

  getListOfPowerConsumptions();
});

function togglePowerConsumptionModal(show) {
  let modal = $("#powerConsumptionModal");
  if (show) {
    let el = $("#powerConsumptionModalTitle");
    if (selectedPowerConsumptionId != 0) {
      el.text("Edit Power Consumption");
    } else {
      el.text("Create Power Consumption");
    }

    modal.modal("show");
  } else {
    modal.modal("hide");
  }
}

async function fetchUsersForPC() {
  await getAllUsersForPC().then((data) => {
    if (data.length > 0) {
      populateUsersForPC(data);
    }
  });
}

function populateUsersForPC(users) {
  let el = $("#pcUser");
  let code = "";
  users.forEach((item) => {
    code += `<option value=${item.userId}>${item.name}</option>`;
  });

  el.children("option:not(:first-child)").remove();
  el.append(code);
}

function getAllUsersForPC() {
  return sendHttpRequest("get", "/Users/all");
}

function savePowerConsumptionClickHandler() {
  let params = {};
  params.Id = selectedPowerConsumptionId;
  params.MobileNumber = $("#pcMobileNumber").val();
  params.Units = $("#pcUnits").val();
  params.UserId = $("#pcUser").val();
  params.BillDate = getDate("pcBillDate");

  if (isNullEmptyUndefinedOrZero(params.UserId)) {
    toastr.warning("Please select a User", "Warning");
    return;
  }

  if (isNullEmptyUndefinedOrZero(params.MobileNumber)) {
    toastr.warning("Please enter the Mobile Number", "Warning");
    return;
  }
  if (!isPhoneNumberValid(params.MobileNumber)) {
    toastr.warning("Please enter a valid Mobile Number", "Warning");
    return;
  }
  if (isNullEmptyUndefinedOrZero(params.Units)) {
    toastr.warning("Please enter the Unit count", "Warning");
    return;
  }

  if (
    isNullEmptyUndefinedOrZero(params.BillDate) ||
    params.BillDate === "Invalid date"
  ) {
    toastr.warning("Please enter the Bill Date", "Warning");
    return;
  }

  console.log(params);

  let url = "";

  if (selectedPowerConsumptionId > 0) {
    url = "/PowerConsumption/edit";
  } else {
    delete params.Id;
    url = "/PowerConsumption/save";
  }

  // var settings = {
  //   url: "http://localhost:8081/ElectroGrid/api/PowerConsumption/save",
  //   method: "POST",
  //   timeout: 0,
  //   headers: {
  //     "Content-Type": "application/x-www-form-urlencoded",
  //   },
  //   data: {
  //     MobileNumber: params.MobileNumber,
  //     Units: params.Units,
  //     BillDate: params.BillDate,
  //     UserId: params.UserId,
  //   },
  // };

  // $.ajax(settings).done(function (response) {
  //   console.log(response);
  // });

  sendHttpRequest("POST", url, null, params).then((data) => {
    if (data.Status === "success") {
      toastr.success("Changes made successfully", "Success");
      getListOfPowerConsumptions();
      togglePowerConsumptionModal();
      clearPowerConsumptionFields();
    }
  });
}

function getListOfPowerConsumptions() {
  powerConsumptionTable.clear().draw();
  sendHttpRequest("GET", "/PowerConsumption/all").then((data) => {
    powerConsumptionTable.rows.add(data).draw();
  });
}

async function editPowerConsumptionClickHandler(rowData) {
  selectedPowerConsumptionId = rowData.id;

  await fetchUsersForPC();

  $("#pcUser").val(rowData.userId);
  $("#pcMobileNumber").val(rowData.mobileNumber);
  $("#pcUnits").val(rowData.units);
  setDate("pcBillDate", rowData.billDate);

  togglePowerConsumptionModal(true);
}

function fetchPowerConsumptionDetailsForId() {
  return sendHttpRequest("get", "/Admin/GetPowerConsumptionDetailsById", {
    id: selectedPowerConsumptionId,
  }).promise();
}

function deletePowerConsumptionClickHandler(data) {
  sendHttpRequest("get", "/PowerConsumption/delete", { id: data.id }).then(
    () => {
      toastr.success("Delete successful", "Success");
      getListOfPowerConsumptions();
    }
  );
}

function clearPowerConsumptionFields() {
  $("#pcMobileNumber,#pcUnits,#pcBillDate").val("");
  $("#pcUser").val("0");

  selectedPowerConsumptionId = 0;
}
