<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/Site.css" />
    <link rel="stylesheet" type="text/css" href="css/all.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.min.css" />
    <link rel="stylesheet" type="text/css" href="css/toastr.min.css" />
    <link rel="stylesheet" type="text/css" href="css/microtip.css" />
    <link
      rel="stylesheet"
      type="text/css"
      href="css/DataTables/css/dataTables.bootstrap.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="css/DataTables/css/select.dataTables.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="css/DataTables/css/buttons.dataTables.min.css"
    />

    <meta charset="ISO-8859-1" />
    <title>Dashboard</title>
  </head>

  <body>
    <div>
      <div id="mainLoader" class="main-loader">
        <div class="dot-floating"></div>
      </div>

      <div class="dashboard">
        <div>
          <div class="dash-left">
            <div class="dash-logo">
              <i class="fas fa-users"></i>
              <h2>ElectroGrid</h2>
            </div>
            <div class="dash-list">
              <ul id="dash-tabs">
                <li id="powerConsumption" class="selected">
                  <i class="fas fa-columns"></i>Power Consumption<span></span>
                </li>
              </ul>
            </div>
            <div></div>
          </div>
          <div id="midContainer" class="dash-mid">
            <jsp:include
              page="partials/dashboard/_PowerConsumption.jsp"
            ></jsp:include>
          </div>
        </div>
      </div>
      <button id="dashLeftMenu" class="menu">
        <i class="fas fa-th-large"></i>
      </button>
      <button id="dashRightMenu" class="user-menu">
        <i class="fas fa-user"></i>
      </button>

      <jsp:include
        page="partials/modals/_PowerConsumptionAddEditModal.jsp"
      ></jsp:include>

      <div id="mobOverlay" class="mob-overlay"></div>
    </div>

    <script src="scripts/app.js"></script>
    <script src="scripts/jquery-3.3.1.min.js"></script>
    <script src="scripts/bootstrap.min.js"></script>
        <script src="scripts/moment.min.js"></script>
    <script src="scripts/bootstrap-datetimepicker.min.js"></script>
    <script src="scripts/layout.js"></script>

    <script src="scripts/toastr.min.js"></script>
    <script src="scripts/viewScripts/Dashboard/dashboard.js"></script>
    <script src="scripts/viewScripts/Dashboard/powerConsumption.js"></script>

    <script src="scripts/DataTables/jquery.dataTables.min.js"></script>
    <script src="scripts/DataTables/dataTables.buttons.min.js"></script>
    <script src="scripts/DataTables/dataTables.select.min.js"></script>
    <script src="scripts/DataTables/buttons.flash.min.js"></script>
    <script src="scripts/DataTables/buttons.html5.min.js"></script>
    <script src="scripts/DataTables/buttons.print.min.js"></script>
    <script src="scripts/jszip.min.js"></script>
  </body>
</html>
