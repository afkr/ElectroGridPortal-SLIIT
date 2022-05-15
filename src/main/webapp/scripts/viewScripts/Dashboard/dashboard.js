"use strict";

$(document).ready(function () {
  authenticateUser();
  instantiateDateTimePickers();

  //tabs
  $("#dash-tabs > li").on("click", toggleTabs.bind(this));
});

function toggleTabs(e) {
  let el = e.currentTarget;

  //tab style
  $("#dash-tabs > li").removeClass("selected");
  $(el).addClass("selected");

  //container preview
  $("#midContainer > div").removeClass("show");
  $(`#${el.id}-container`).addClass("show");

  //functions
  if (el.id === "powerConsumption") {
    getListOfPowerConsumptions();
  }
}

function authenticateUser() {
  let user = getSessionForDefault("user");
  if (isNullEmptyUndefinedOrZero(user)) {
    window.location.href = "/ElectroGridPortal/index.jsp";
  }
  else{
  	$("#loggedUserName").text(user.name);
  }
}
