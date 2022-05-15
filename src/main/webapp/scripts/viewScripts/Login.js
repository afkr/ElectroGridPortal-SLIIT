"use strict";

$(document).ready(function () {
  authenticateUser();

  document.getElementById("username").focus();

  $("#password, #username").keypress(function (e) {
    var key = e.which;
    if (key === 13) {
      loginHandler();
    }
  });

  $("#login").on("click", function () {
    loginHandler();
  });
});

function loginHandler() {
  let username;
  let password;

  username = $("#username").val();
  password = $("#password").val();

  if (isNullEmptyUndefinedOrZero(username)) {
    toastr.warning("Please enter your username", "Warning");
  } else if (isNullEmptyUndefinedOrZero(password)) {
    toastr.warning("Please enter your password", "Warning");
  } else {
    $("#loginLoad").css("display", "block");
    let user = {
      username,
      password,
    };

    sendHttpRequest("post", "/PowerConsumption/login", null, user).then((data) => {
      $("#loginLoad").css("display", "none");
      if (data.Status === "success") {
        setSessionForDefault("user", JSON.parse(data.Data));
        window.location.href = "/ElectroGridPortal/dashboard.jsp";
      }
    });
  }
}

function authenticateUser() {
  let user = getSessionForDefault("user");
  if (!isNullEmptyUndefinedOrZero(user)) {
    window.location.href = "/ElectroGridPortal/dashboard.jsp";
  }
}
