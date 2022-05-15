$(document).ready(function () {
  $("#dashLeftMenu").click(() => toggleDashLeftMenu(true));
  $("#dashRightMenu").click(() => toggleDashRightMenu(true));

  $("#mobOverlay,#dash-tabs > li").click(closeMenu);

  $("#logout").click(logout);
});

function toggleDashLeftMenu(show) {
  if (show) {
    $(".dash-left").addClass("menu-show");
  } else {
    $(".dash-left").removeClass("menu-show");
  }
  toggleMobLay(true);
}

function toggleDashRightMenu(show) {
  if (show) {
    $(".dash-right").addClass("menu-show");
  } else {
    $(".dash-right").removeClass("menu-show");
  }
  toggleMobLay(true);
}

function closeMenu() {
  toggleDashLeftMenu();
  toggleDashRightMenu();
  toggleMobLay();
}

function toggleMobLay(show) {
  if (show) {
    $("#mobOverlay").addClass("lay");
  } else {
    $("#mobOverlay").removeClass("lay");
  }
}

function logout() {
  clearSessionForDefault("user");
  window.location.reload();
}
