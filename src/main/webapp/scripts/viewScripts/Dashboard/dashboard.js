"use strict";

$(document).ready(function () {
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