// TODO
// - The number of registered users.
// - Total bookings for the day.
// - The number of available and reserved cars.
// - The number of bookings active for the day.
// - The number of available and occupied drivers.

$(document).ready(function () {
  $("body>section").hide();
  $("section#admin").show();
  $(".nav-link").click(function () {
    changeActiveTab($(this).attr("id"));
    console.log($(this).attr("id"));
  });
  $(".datepicker").datepicker();
});

$("#logOut").click(function () {
  cookieTable.user = null;
  location.reload();
});

var cookieTable = {};

function changeActiveTab(tab) {
  $("body>section").hide();
  $("body>div").hide();
  $(tab).show();
}

let baseURL = "http://localhost:8080/EasyCarRental_war/";

// -------------------------------dashboard------------------------------------

async function loadDashboardData() {
  let occupiedDrivers = await $.get(baseURL + "driver/getNoOfOccupiedDrivers");
  let availableDrivers = await $.get(
    baseURL + "driver/getNoOfAvailableDrivers"
  );
  let activeBookings = await $.get(baseURL + "booking/getBookingActiveToday");
  let bookingsForToday = await $.get(baseURL + "booking/getBookingsForToday");
  let availableCars = await $.get(
    baseURL + "car/countCarsByAvailabilityIsTrue"
  );
  let scheduledCars = await $.get(baseURL + "car/countCarsScheduled");
  let ob = {
    occupiedDrivers,
    availableDrivers,
    activeBookings,
    bookingsForToday,
    availableCars,
    scheduledCars,
  };
  console.log(ob);

  $("section#dashboard span").each(function () {
    console.log($(this).attr("id"));
    $(this).text(ob[$(this).attr("id")].data);
  });
}

loadDashboardData();

// ------------------------- admin --------------------------------

$("#admin-login").click(function () {
  console.log("clicked");
  let data = $("#adminLogin").serialize();
  $.ajax({
    url: baseURL + "user/admin",
    method: "post",
    dataType: "json",
    data: data,
    success: function (res) {
      if (res.data) {
        // login success
        alert(res.message);
        cookieTable.user = res.data;
        $(".navbar-toggler").show();
        changeActiveTab("#dashboard");
      } else {
        alert(res.message);
      }
    },
    error: function (error) {
      // var jsObject = JSON.parse(error.responseText);
      // alert(jsObject.message);
    },
  });
  // clear form
  $("#adminLogin")[0].reset();
});
// ------------------------- driver ------------------------------

function clearDriverForm() {
  $("form#driver")[0].reset();
}
function prepareDriverForm(url, method) {
  var form = $("form#driver").serializeArray();
  var formData = new FormData();
  // let DATA = {}
  // $.each(form, function (key, input) {
  //   DATA[input.name]=input.value;
  // });
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
      clearDriverForm();
      loadAllDrivers();
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
  prepareDriverForm("driver/update", "post");
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
          "</td><td>" +
          "<i class='fa-sharp fa-solid fa-trash del driver-del'></i>" +
          "&nbsp;&nbsp;&nbsp;&nbsp;" +
          "<i class='fa-solid fa-pen-to-square driver-up'></i>" +
          "</td></tr>";
        $("#driver-tb").append(row);
      }
      $(".driver-del").click(function () {
        let username = $(this).parent().parent().children(":eq(1)").text();
        console.log(username);
        $.ajax({
          url: baseURL + "driver/remove?username=" + username,
          method: "delete",
          dataType: "json",
          success: function (res) {
            alert(res.message);
            // load
            loadAllDrivers();
          },
          error: function (error) {
            var jsObject = JSON.parse(error.responseText);
            alert(jsObject.message);
          },
        });
      });
      $(".driver-up").click(function () {
        let username = $(this).parent().parent().children(":eq(1)").text();
        console.log(username);
        $.ajax({
          url: baseURL + "driver/find?username=" + username,
          method: "get",
          dataType: "json",
          success: function (res) {
            console.log(res.data);
            let {
              username,
              password,
              fname,
              lname,
              contactNo,
              profile,
              license,
            } = res.data;
            $("#dUsername").val(username);
            $("#dPassword").val(password);
            $("#dRptPassword").val(password);
            $("#dLname").val(lname);
            $("#dFname").val(fname);
            $("#dContact").val(contactNo);
            $("#dLicense").val(license);
          },
          error: function (error) {
            var jsObject = JSON.parse(error.responseText);
            alert(jsObject.message);
          },
        });
      });
    },
  });
}

loadAllDrivers();

// --------------------  car ------------------------------------

function clearCarRegister() {
  $("form#carAdd")[0].reset();
}
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
      clearCarRegister();
      loadAllCars();
    },
    error: function (error) {
      alert(error);
    },
  });
}
// car-add
$("#carSave").click(function () {
  prepareCarForm("car/save", "post");
});
// car-update
$("#carUpdate").click(function () {
  prepareCarForm("car/update", "post");
});
function loadAllCars() {
  $(".select-car").empty();
  $.ajax({
    url: baseURL + "car/getAll",
    dataType: "json",
    success: function (resp) {
      console.log(resp.data);
      resp.data.forEach((car) => {
        let { brand, model, fuelType, registrationNumber } = car;
        $(".select-car").append(
          '<div class="container"> ' +
            '<section class="mx-auto my-5" style="max-width: 23rem;"> ' +
            '<div class="card">' +
            '<div class="bg-image hover-overlay ripple" ' +
            'data-mdb-ripple-color="light"> ' +
            '<img src="../assets/images/car-10.jpg" ' +
            'class="img-fluid" />' +
            '<a href="#!">' +
            '<div class="mask"' +
            'style="background-color: rgba(251, 251,' +
            '251, 0.15);">' +
            "</div>" +
            "</a>" +
            "</div>" +
            '<div class="card-body">' +
            '<h5 class="card-title font-weight-bold" id="lblCarBrand">' +
            "<a>" +
            brand +
            " " +
            model +
            "</a></h5>" +
            '<p class="mb-2" id="lblCarFuelType">' +
            fuelType +
            "</p>" +
            '<p class="mb-2" id="lblCarRegNo">' +
            registrationNumber +
            "</p>" +
            '<hr class="my-4" />' +
            '<a href="#!"' +
            'class="btn btn-link link-secondary p-md-1 mb-0 car-up">Update</a>' +
            '<a href="#!"' +
            'class="btn btn-link link-primary p-md-1 mb-0 car-del">Remove</a>' +
            "</div>" +
            "</div>" +
            "</section>" +
            "</div>"
        );
      });
      // button functionalities
      $(".car-up").click(function () {
        let regNo = $(this).parent().children("p#lblCarRegNo").text();
        $.ajax({
          url: baseURL + "car/find?registrationNumber=" + regNo,
          method: "get",
          dataType: "json",
          success: function (res) {
            let values = res.data;
            console.log(values);
            var inputs = $("#carAdd").find(":input");
            console.dir(inputs);
            inputs.each(function () {
              $(this).val(values[this.name]);
            });
            // loadAllCars();
          },
          error: function (error) {
            // var jsObject = JSON.parse(error.responseText);
            // alert(jsObject.message);
          },
        });
      });
      // delete
      $(".car-del").click(function () {
        let regNo = $(this).parent().children("p#lblCarRegNo").text();
        $.ajax({
          url: baseURL + "car/remove?registrationNumber=" + regNo,
          method: "delete",
          dataType: "json",
          success: function (res) {
            alert(res.message);
            loadAllCars();
          },
          error: function (error) {
            // var jsObject = JSON.parse(error.responseText);
            // alert(jsObject.message);
          },
        });
      });
    },
  });
}
loadAllCars();

// ------------------booking-----------------------------

async function loadAllBookingsToBeAccepted() {
  $.ajax({
    url: baseURL + "booking/getBookingsByAcceptedFalse",
    method: "get",
    dataType: "json",
    success: function (res) {
      let bookings = res.data;
      console.dir(bookings);
      $("div#booking-row").empty();
      bookings.forEach((data) => {
        console.log("run run");
        $("div#booking-row").append(
          '<div class="card text-center col-6">' +
            '<div class="card-header">' +
            '<span id="bookingId">' +
            data.bookingId +
            "</span>" +
            "</div>" +
            '<div class="row booking-row">' +
            '<div class="card-body col-6">' +
            '<h5 class="card-title">Booked By : <span id="user"> ' +
            data.userId +
            "</span></h5>" +
            '<h5 class="card-title">Driver : <span id="driver">' +
            data.carId +
            "</span></h5>" +
            '<h5 class="card-title">Car Assigned : <span id="car">' +
            data.driverId +
            "</span></h5>" +
            '<a href="#" class="btn btn-primary" id="bkAccept">Accept</a> &nbsp;&nbsp;' +
            '<a href="#" class="btn btn-secondary" id="bkdecline">Decline</a>' +
            "</div>" +
            '<div class="card-body col-6 d-flex justify-content-center flex-wrap">' +
            '<h5 class="card-title">booking Confirmation</span></h5>' +
            "<div" +
            'style="width: 250px; height: 100px; background-color: rgba(127, 255, 212, 0.499); border-radius: 5pt;">' +
            '<a href="#" class="btn btn-secondary mt-4">View</a>' +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>"
        );
        // add button functionalities
        $("#bkAccept").click(function () {
          let id = data.bookingId;

          $.ajax({
            url: baseURL + "booking/accept?id=" + id,
            method: "post",
            dataType: "json",
            success: function (res) {
              console.log(res.messege);
              alert(res.message);
              loadAllBookingsToBeAccepted();
            },
            error: function (error) {
              // var jsObject = JSON.parse(error.responseText);
              // alert(jsObject.message);
            },
          });
        });
        $("#bkdecline").click(async function () {
          let id = data.bookingId;
          $.ajax({
            url: baseURL + "booking/delete?id=" + id,
            method: "delete",
            dataType: "json",
            success: function (res) {
              console.log(res.messege);
              alert(res.message);
              loadAllBookingsToBeAccepted();
            },
            error: function (error) {
              // var jsObject = JSON.parse(error.responseText);
              // alert(jsObject.message);
            },
          });
        });
      });
    },
    error: function (error) {
      // var jsObject = JSON.parse(error.responseText);
      // alert(jsObject.message);
    },
  });
}

loadAllBookingsToBeAccepted();

$("#payBtn").click(function () {
  let paymentDetails = $("form#paymentForm").serialize();
  console.log(paymentDetails);
  paymentDetails += "&rent=" + $("span#rent").text();
  $.ajax({
    url: baseURL + "booking/payment",
    data: paymentDetails,
    method: "post",
    dataType: "json",
    success: function (res) {
      alert(res.message);
      loadAllCars();
    },
    error: function (error) {
      // var jsObject = JSON.parse(error.responseText);
      // alert(jsObject.message);
    },
  });
});
