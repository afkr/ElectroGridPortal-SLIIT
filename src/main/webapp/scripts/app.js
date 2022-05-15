function sendHttpRequest(
  method,
  url,
  params = null,
  data = null,
  contentType = "application/x-www-form-urlencoded",
  handleErrors = true
) {
  url = params ? url + "?" + constructUrlWithParamsForFetch(params) : url;
  let domain = "http://localhost:8081/ElectroGrid/api";
  var request = $.ajax({
    method: method,
    url: domain + url,
    data: data,
    cache: false,
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    dataType: "json",
  });

  if (handleErrors) {
    request.then((data) => {
      if (data.Status === "warning") {
        toastr.warning(data.Message, "Warning");
      } else if (data.Status === "error") {
        toastr.error(data.Message, "Error");
      }
    });
  }
  return request.promise();
}

function getDate(id) {
  return moment($(`#${id}`).data("DateTimePicker").date()).format("DD-MM-YYYY");
}

function setDate(id, date, format = "DD-MMM-YYYY") {
  $(`#${id}`).data("DateTimePicker").date(moment(date).format(format));
}

function constructUrlWithParamsForFetch(params) {
  var esc = encodeURIComponent;
  var query = Object.keys(params)
    .map((k) => esc(k) + "=" + esc(params[k]))
    .join("&");
  return query;
}

function isNullEmptyUndefinedOrZero(param) {
  var isNEU = false;
  param = param;
  if (param == undefined || param == null || param == "" || param == 0) {
    isNEU = true;
  }
  return isNEU;
}

function isNullEmptyOrUndefined(param) {
  var isNEU = false;
  param = param;
  if (param == undefined || param == null || param == "") {
    isNEU = true;
  }
  return isNEU;
}
function isNullEmptyOrUndefined(param) {
  var isNEU = false;
  param = param;
  if (param == undefined || param == null || param == "") {
    isNEU = true;
  }
  return isNEU;
}

function isEmailValid(param) {
  param = param.trim();
  const regex = new RegExp(
    /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  );
  return regex.test(param);
}

function isPhoneNumberValid(param) {
  param = param.trim();
  const regex = new RegExp(/\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{3})/);
  return regex.test(param);
}

function getUrlVars() {
  var vars = [],
    hash;
  var hashes = window.location.href
    .slice(window.location.href.indexOf("?") + 1)
    .split("&");
  for (var i = 0; i < hashes.length; i++) {
    hash = hashes[i].split("=");
    vars.push(hash[0]);
    vars[hash[0]] = hash[1];
  }
  return vars;
}

function setSessionForDefault(key, value) {
  var itemSession = btoa(JSON.stringify(value));
  sessionStorage.setItem(key, itemSession);
}

function getSessionForDefault(key) {
  var itemSession = sessionStorage.getItem(key);
  var value;
  if (itemSession) {
    value = JSON.parse(atob(itemSession)) || 0;
  }
  return value;
}
function clearSessionForDefault(key) {
  sessionStorage.removeItem(key);
}

function instantiateDateTimePickers() {
  //date
  $(".datetimepicker.date").datetimepicker({
    format: "DD-MMM-YYYY",
    icons: {
      time: "fas fa-clock",
      date: "fa fa-calendar",
      up: "fa fa-arrow-up",
      down: "fa fa-arrow-down",
      previous: "fa fa-chevron-left",
      next: "fa fa-chevron-right",
      today: "fas fa-clock",
      clear: "fas fa-trash",
    },
  });
  //date n time
  $(".datetimepicker.dateTime").datetimepicker({
    format: "DD-MMM-YYYY HH:mm",
    icons: {
      time: "fas fa-clock",
      date: "fa fa-calendar",
      up: "fa fa-arrow-up",
      down: "fa fa-arrow-down",
      previous: "fa fa-chevron-left",
      next: "fa fa-chevron-right",
      today: "fas fa-clock",
      clear: "fas fa-trash",
    },
  });

  //time
  $(".datetimepicker.time").datetimepicker({
    format: "HH:mm",
    icons: {
      time: "fas fa-clock",
      date: "fa fa-calendar",
      up: "fa fa-arrow-up",
      down: "fa fa-arrow-down",
      previous: "fa fa-chevron-left",
      next: "fa fa-chevron-right",
      today: "fas fa-clock",
      clear: "fas fa-trash",
    },
  });

  //month
  $(".datetimepicker.month").datetimepicker({
    format: "MMM",
    viewMode: "months",
    icons: {
      time: "fas fa-clock",
      date: "fa fa-calendar",
      up: "fa fa-arrow-up",
      down: "fa fa-arrow-down",
      previous: "fa fa-chevron-left",
      next: "fa fa-chevron-right",
      today: "fas fa-clock",
      clear: "fas fa-trash",
    },
  });

  //year
  $(".datetimepicker.year").datetimepicker({
    format: "YYYY",
    icons: {
      time: "fas fa-clock",
      date: "fa fa-calendar",
      up: "fa fa-arrow-up",
      down: "fa fa-arrow-down",
      previous: "fa fa-chevron-left",
      next: "fa fa-chevron-right",
      today: "fas fa-clock",
      clear: "fas fa-trash",
    },
  });
}

function returnZeroForNaN(param) {
  if (isNaN(param)) {
    param = 0;
  }
  return param;
}

function dataURItoBlob(dataURI) {
  // convert base64 to raw binary data held in a string
  // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
  var byteString = atob(dataURI.split(",")[1]);

  // separate out the mime component
  var mimeString = dataURI.split(",")[0].split(":")[1].split(";")[0];

  // write the bytes of the string to an ArrayBuffer
  var ab = new ArrayBuffer(byteString.length);

  // create a view into the buffer
  var ia = new Uint8Array(ab);

  // set the bytes of the buffer to the correct values
  for (var i = 0; i < byteString.length; i++) {
    ia[i] = byteString.charCodeAt(i);
  }

  // write the ArrayBuffer to a blob, and you're done
  var blob = new Blob([ab], { type: mimeString });
  return blob;
}

function downloadURI(uri) {
  var link = document.createElement("a");
  link.setAttribute("download", "");
  link.href = uri;
  document.body.appendChild(link);
  link.click();
  link.remove();
}

function initializeSelectDropdowns() {
  $(".select-2-dropdown").select2();
}

function toggleMainLoader(show) {
  let el = $("#mainLoader");
  if (show) {
    el.addClass("showLay");
  } else {
    el.removeClass("showLay");
  }
}

function populateCapacityBar(consumedSpace, barId) {
  let totalCapacity = 1024 * 10; //10mb
  let usedSpacePercent = (consumedSpace / totalCapacity) * 100.0;

  let usedSpaceMb = consumedSpace / 1024;
  let usedSpaceMbRounded = Math.round(usedSpaceMb * 10) / 10;

  $(`#${barId}-bar`).css("width", `${usedSpacePercent}%`);
  $(`#${barId}-label`).text(`${usedSpaceMbRounded}/10 MB`);
}
