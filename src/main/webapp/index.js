$(function () {
  let activeTab = $("#home");

  $(".datepicker").datepicker();
  console.log("date updated");

  $("#tab-register").click(function () {
    $("#tab-login").removeClass("active");
    $(this).addClass("active");
    $("#pills-login").fadeOut();
    $("#pills-register").fadeIn();
  });

  $("#tab-login").click(function () {
    $("#tab-register").removeClass("active");
    $(this).addClass("active");
    $("#pills-register").fadeOut();
    $("#pills-login").fadeIn();
  });

  // tab navigation
  var navButtons = [
    $("#navToItems"),
    $("#navToCustomers"),
    $("#navToOrders"),
    $("#navToHome"),
    $("#navToViewOrderDetails"),
  ];
  var sections = [
    $("#car-selections"),
    $("#home"),
    $("#car-single-selection"),
    $("#driver-management"),
    $("#user-bookings"),
  ];

  let baseURL = "http://localhost:8080/EasyCarRental_war/";

  // save customer
  $("form#userAdd").submit(function (e) {
    e.preventDefault();
    var form = $("#userAdd").serializeArray();
    var formData = new FormData();

    //Form data
    $.each(form, function (key, input) {
      formData.append(input.name, input.value);
    });

    formData.append("file1", $("#inputGroupFile02")[0].files[0]);
    formData.append("file2", $("#inputGroupFile01")[0].files[0]);

    $.ajax({
      url: baseURL + "user/add",
      method: "post",
      data: formData,
      dataType: "json",
      success: function (res) {
        alert(res.message);
      },
      error: function (error) {
        var jsObject = JSON.parse(error.responseText);
        alert(jsObject.message);
      },
      processData: false,
      contentType: false,
    });
  });

  // --------------------  car ------------------------------------

  function prepareCarForm(url, method) {
    var form = $("#carAdd").serializeArray();
    var formData = new FormData();
    $.each(form, function (key, input) {
      formData.append(input.name, input.value);
    });

    formData.append("file1", $("#f-car-front")[0].files[0]);
    formData.append("file2", $("#f-car-back")[0].files[0]);
    formData.append("file3", $("#f-car-side")[0].files[0]);
    formData.append("file4", $("#f-car-interior")[0].files[0]);
    $.ajax({
      url: baseURL + url,
      method: method,
      processData: false,
      contentType: false,
      dataType: "json",
      data: formData,
      success: function (res) {
        alert(res.message);
      },
      error: function (error) {
        var jsObject = JSON.parse(error.responseText);
        alert(jsObject.message);
      },
    });
  }
  // car-add
  $("#carSave").click(function () {
    prepareCarForm("car/save", "post");
  });
  // car-update
  $("#carUpdate").click(function () {
    prepareCarForm("car/update", "put");
  });
  function loadAllCars() {
    $(".select-car").empty();
    $.ajax({
      url: baseURL + "car/getAll",
      dataType: "json",
      success: function (resp) {
        console.log(resp);
        //  load cards
      },
    });
  }
  loadAllCars();
  // ------------------------- driver ------------------------------

  function prepareDriverForm(url, method) {
    var form = $("form#driver").serializeArray();
    var formData = new FormData();
    $.each(form, function (key, input) {
      formData.append(input.name, input.value);
    });

    formData.append("file1", $("#driver-pro")[0].files[0]);

    $.ajax({
      url: baseURL + url,
      method: method,
      processData: false,
      contentType: false,
      dataType: "json",
      data: formData,
      success: function (res) {
        alert(res.message);
      },
      error: function (error) {
        var jsObject = JSON.parse(error.responseText);
        alert(jsObject.message);
      },
    });
  }

  $("button#driverSave").click(function () {
    console.log("cicked");
    prepareDriverForm("driver/add", "post");
  });

  $("button#driverUpdate").click(function () {
    prepareDriverForm("driver/update", "put");
  });
  function loadAllDrivers() {
    $("#driver-tb").empty();
    $.ajax({
      url: baseURL + "driver/getAll",
      dataType: "json",
      success: function (resp) {
        console.log(resp);
        for (let driver of resp.data) {
          var row =
            "<tr><td>" +
            driver.fname +
            " " +
            driver.lname +
            "</td><td>" +
            driver.username +
            "</td><td>" +
            driver.contactNo +
            "</td><td>" +
            driver.license +
            "</td></tr>";
          $("#driver-tb").append(row);
        }
      },
    });
  }
  loadAllDrivers();
});
