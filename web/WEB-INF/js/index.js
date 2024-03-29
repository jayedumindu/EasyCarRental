$(function () {
  //  navigation and event handling
  // cookies - user,license,nic
  var cookieTable = {};
  $(document).ready(function () {
    $("body>section").hide();
    $("section#home").show();
    $("#logout").hide();
    $("li#user").hide();
    $(".datepicker").datepicker({
      onSelect: calculateValueOnDateChange,
    });

    let driver_login_collapse_btn = $("a#driver-login-collapse").data(
      "clicks",
      0
    );
    driver_login_collapse_btn.click(function () {
      var click = $(this).data("clicks");
      console.log(click);
      if (click % 2 === 1) {
        $("#driver-overlay").removeClass("justify-content-center");
        $("div.overlay").show();
      } else {
        $("#driver-overlay").addClass("justify-content-center");
        $("div.overlay").hide();
      }
      $(this).data("clicks", click + 1);
    });
  });

  function changeActiveTab(tab) {
    $("body>section").hide();
    $(tab).show();
    $("html, body").animate({ scrollTop: 0 }, "fast");
    clearBookingForm();
  }

  // for all nav links
  $(".navbar-main .nav-item").click(function () {
    let path = $(this).children("a").attr("href");
    console.log(path);
    changeActiveTab(path);
  });

  $(".img-upload-select").on("click", function (event) {
    console.log("clicked");
    let elem = $(event.target);
    let input = $(elem).parent().find("input.file-input");
    input.trigger("click");
  });

  $(".file-input").change(function (event) {
    const file = event.target.files[0];
    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    if ($(event.target).attr("id") == "nic") {
      cookieTable.nic = file;
    } else cookieTable.license = file;
    console.log(cookieTable);
    fileReader.onload = function () {
      $(event.target)
        .parent()
        .parent()
        .find(".img-upload-select")
        .css("background-image", `url(${fileReader.result})`);
    };
  });

  $("#tab-register").click(function () {
    $("#tab-login").removeClass("active");
    $(this).addClass("active");
    $("#pills-login").hide();
    $("#pills-register").fadeIn();
  });

  $("#tab-login").click(function () {
    $("#tab-register").removeClass("active");
    $(this).addClass("active");
    $("#pills-register").hide();
    $("#pills-login").fadeIn();
  });

  $("#logout").click(function () {
    location.reload();
  });

  let baseURL = "http://localhost:8080/EasyCarRental_war/";

  // ---------------------------- home ---------------------------------
  // load featured cars
  function loadFeaturesCars(carList) {
    carList.forEach((car) => {
      $(".featured-row").append(
        '<div class="card" style="width: 23rem; margin:10pt;">' +
          '<div class="bg-image hover-overlay ripple" ' +
          'data-mdb-ripple-color="light"> ' +
          `<img src="data:image/jpeg;base64, ${car.img_front}" ` +
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
          car.brand +
          " " +
          car.model +
          "</a></h5>" +
          '<p class="mb-2" id="lblCarFuelType">' +
          car.fuelType +
          "</p>" +
          '<p class="mb-2" id="lblCarRegNo">' +
          car.registrationNumber +
          "</p>" +
          // '<hr class="my-4" />' +
          // '<a href="#!"' +
          // 'class="btn btn-link link-secondary p-md-1 mb-0 car-up">Update</a>' +
          // '<a href="#!"' +
          // 'class="btn btn-link link-primary p-md-1 mb-0 car-del">Remove</a>' +
          "</div>" +
          "</div>"
      );
    });
  }

  // ------------------------------------- user -------------------------

  // clear login form
  function clearLogin() {
    // $("form#userLogin :input").val("");
    $("form#userLogin")[0].reset();
  }

  function clearRegister() {
    $("form#userAdd")[0].reset();
    cookieTable.license, (cookieTable.nic = null);
    $(".img-upload-select").css("background-image", "none");
  }

  // user login
  $("#user-login").click(function () {
    console.log("called");
    let [uname, pwd] = [$("#loginUsername").val(), $("#loginPassword").val()];
    clearLogin();
    $.ajax({
      url: baseURL + "user/find?username=" + uname + "&pwd=" + pwd,
      method: "get",
      dataType: "json",
      success: function (res) {
        console.log(res.data);
        if (res.data) {
          // adding cookies
          $.cookie("userLoggedIn", true, { path: "/" });
          customAlert.alert("successful", "User Login");
          // after logged in
          $("#user-management-inner").hide();
          $("#logout").show();
          $("li#user").show();
          loadDataForUserSection(uname);
          changeActiveTab("#user-bookings");
        } else {
          customAlert.alert("Wrong Credentials", "User Login");
        }
      },
      error: function (error) {
        customAlert.alert(error.responseText, "User Login");
      },
    });
  });

  function loadDataForUserSection(uname) {
    $.ajax({
      url: baseURL + "user/findOne?username=" + uname,
      method: "get",
      dataType: "json",
      success: function (res) {
        cookieTable.user = res.data;
        $(".contact-section span").each(function () {
          $(this).text(cookieTable.user[$(this).attr("id")]);
        });

        loadAllBookingsForUser(uname);
      },
      error: function (error) {
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
    });
  }
  // save customer
  $("form#userAdd").submit(function (e) {
    e.preventDefault();
    var form = $("#userAdd").serializeArray();
    var formData = new FormData();

    //Form data
    $.each(form, function (key, input) {
      formData.append(input.name, input.value);
    });

    formData.append("file1", cookieTable.nic);
    formData.append("file2", cookieTable.license);

    clearRegister();
    $.ajax({
      url: baseURL + "user/add",
      method: "post",
      data: formData,
      dataType: "json",
      success: function (res) {
        customAlert.alert(res.message, "User Register");
      },
      error: function (error) {
        customAlert.alert("Unsuccesfull...Please try again", "User Register");
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
      processData: false,
      contentType: false,
    });
  });

  $("#updateUser").click(function () {
    // update user
    let data = $("form#user-up").serialize();
    data += "&name=" + $("input#fname").val() + " " + $("input#lname").val();

    data += "&username=" + $("span#username").text();
    $.ajax({
      url: baseURL + "user/update",
      method: "post",
      data: data,
      dataType: "json",
      success: function (res) {
        customAlert.alert(
          res.message + " : please log in to proceed",
          "User Register"
        );
        location.reload();
      },
      error: function (error) {
        customAlert.alert("Unsuccesfull...Please try again", "User Update");
      },
    });
  });

  function loadAllBookingsForUser(uname) {
    $("tbody#userBookings").empty();
    // ajax
    $.ajax({
      url: baseURL + "booking/pending?username=" + uname,
      method: "get",
      dataType: "json",
      success: function (res) {
        console.dir(res.data);
        res.data.forEach(function (booking) {
          let [yyyy, mm, dd] = booking.currenDateTime;
          $("tbody#userBookings").append(
            '<tr class="">' +
              '<td class="car-image">' +
              `<img class='img' src="data:image/jpeg;base64, ${booking.car.img_front}">` +
              "</td>" +
              '<td class="product-name">' +
              "<h3>" +
              `${booking.car.brand} ${booking.car.model}` +
              "</h3>" +
              `<p>${booking.car.registrationNumber}</p>` +
              "</td>" +
              '<td class="price" colspan="3">' +
              '<p class="btn-custom">' +
              '<a class="bk-cancel">Cancel Booking</a>' +
              "</p>" +
              '<div class="price-rate">' +
              "<h3>" +
              `<span id="booking">${booking.bookingId}</span>` +
              `<span class="per">booked on ${yyyy}-${mm}-${dd}</span>` +
              "</h3>" +
              `<span class="subheading">${booking.car.mileage} KMs done</span>` +
              "</div>" +
              "</td>" +
              "</tr>"
          );
          // btn behaviours
          $(".bk-cancel").click(function () {
            id = $(this).parent().parent().find("span#booking").text();
            $.ajax({
              url: baseURL + "booking/delete?id=" + id,
              method: "delete",
              dataType: "json",
              success: function (res) {
                alert(res.message);
                loadAllBookingsForUser(cookieTable.user.username);
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

  // ------------------------------- cars -------------------

  // load car cards
  function loadAllCarsForSelection() {
    $("#car-selection-row").empty();
    $.ajax({
      url: baseURL + "car/getAll",
      dataType: "json",
      success: function (resp) {
        cookieTable.cars = resp.data;
        resp.data.forEach((car) => {
          appendCar(car);
        });
        let allCars = cookieTable.cars;
        loadFeaturesCars([allCars[0], allCars[2], allCars[4]]);
        // button functionalities
        $(".book-btn").click(function () {
          // is user is logged in
          if (cookieTable.user) {
            let regNo = $(this)
              .parent()
              .parent()
              .children(".car-reg-no")
              .text();
            $.ajax({
              url: baseURL + "car/find?registrationNumber=" + regNo,
              method: "get",
              dataType: "json",
              success: function (res) {
                let car = res.data;
                $("#car-single-selection span[id]").each(function () {
                  $(this).text(car[$(this).attr("id")]);
                });
                // load images for carousel
                let { img_front, img_back, img_interior, img_side } = car;
                let imgArr = [img_front, img_back, img_interior, img_side];
                $("#car-single-selection img").each(function (index) {
                  $(this).attr(
                    "src",
                    `data:image/jpeg;base64, ${imgArr[index]}`
                  );
                });
                let advance = 0.0;
                switch (car.type) {
                  case "General":
                    advance = 10000.0;
                    break;
                  case "Premium":
                    advance = 15000.0;
                    break;
                  case "Luxury":
                    advance = 20000.0;
                    break;
                }
                $("span#advancePayment").text(advance);
                changeActiveTab("#car-single-selection");
              },
              error: function () {
                return null;
              },
            });
          } else {
            alert("please Login to proceed!");
          }
        });
      },
    });
  }

  // buffer convertor
  function arrayBufferToBase64(buffer) {
    var blob = new Blob([buffer], { type: "application/octet-binary" });
    return (url = URL.createObjectURL(blob));

    // var binary = "";
    // var bytes = new Uint8Array(buffer);
    // var len = bytes.byteLength;
    // for (var i = 0; i < len; i++) {
    //   binary += String.fromCharCode(bytes[i]);
    // }
    // return window.btoa(binary);
    // return btoa((encodeURIComponent(buffer)));
  }

  function imageOnLoad() {
    console.log("runs");

    // let image = new Image;
    // image.onload() = function () {
    //   URL.revokeObjectURL(url)
    // }
  }

  loadAllCarsForSelection();

  function appendCar(car) {
    let {
      brand,
      model,
      fuelType,
      monthlyRate,
      dailyRate,
      registrationNumber,
      img_front,
    } = car;
    let url = arrayBufferToBase64(img_front);
    console.log(url);
    $("#car-selection-row").append(
      '<div class="col-md-4" style="overflow:hidden;">' +
        '<div class="car-wrap rounded ">' +
        '<div class="img rounded d-flex" style="justify-content:center;overflow:hidden;">' +
        `<img src="data:image/png;base64, ${img_front}" class="car-card-img">` +
        "</div>" +
        '<div class="text">' +
        '<h2 class="mb-0">' +
        "<a>" +
        brand +
        " " +
        model +
        " (" +
        fuelType +
        ")" +
        "</a>" +
        "</h2>" +
        '<h6 class="car-reg-no">' +
        registrationNumber +
        "</h6>" +
        '<div class="d-flex mb-3">' +
        '<br><p class="price ml-auto">' +
        dailyRate +
        "<span>/day</span>" +
        "</p>" +
        '<br><p class="price ml-auto">' +
        monthlyRate +
        "<span>/month</span>" +
        "</p>" +
        "</div>" +
        '<p class="d-flex mb-0 d-block">' +
        '<a class="btn btn-primary py-2 mr-1 book-btn">' +
        "Book now" +
        "</a>" +
        "</p>" +
        "</div>" +
        "</div>" +
        "</div>"
    );
  }

  //  data sorting
  $(".dropdown").on("show.bs.dropdown hide.bs.dropdown", function () {
    // calculation goes here
    //  for each combo
    let attr = [];
    $(".sorting .dropdown-toggle").each(function (index) {
      attr[index] = $(this).val();
    });

    console.log(attr);
    $("#car-selection-row").empty();
    cookieTable.cars.forEach((car) => {
      console.log(car);
      if ((car.brand == attr[0]) | (attr[0] == "")) {
        let [from, to] = attr[1].split("-");
        if (
          ((car.monthlyRate >= from) & (car.monthlyRate < to)) |
          (attr[1] == "")
        ) {
          if ((car.fuelType == attr[2]) | (attr[2] == "")) {
            if ((car.noOfPassengers == attr[3]) | (attr[3] == "")) {
              if ((car.transmissionType == attr[4]) | (attr[4] == "")) {
                appendCar(car);
              }
            }
          }
        }
      }
    });
  });

  // clear sorting
  let defVals = ["Brand", "Price", "Fuel Type", "Passengers", "Transmission"];
  $(".clear-sorting").click(function () {
    $(".sorting .dropdown-toggle").each(function (index) {
      $(this).text(defVals[index]);
    });
    loadAllCarsForSelection();
  });

  $(".dropdown-menu li a").click(function () {
    $(this).closest(".dropdown").find(".dropdown-toggle").text($(this).text());
    $(this).closest(".dropdown").find(".dropdown-toggle").val($(this).text());
  });

  $("#pills-driver").hide();
  $("#nav-profile-tab").hide();
  // on date change
  function calculateValueOnDateChange() {
    // validating date
    let startDay = new Date($("#datepicker1").val());
    let endDay = new Date($("#datepicker2").val());
    let today = Date.now();
    let total = 0.0;
    if (startDay & endDay) {
      if ((startDay < endDay) & (startDay >= today)) {
        try {
          var millisBetween = startDay.getTime() - endDay.getTime();
        } finally {
          let totalDays = Math.round(
            Math.abs(millisBetween / (1000 * 3600 * 24))
          );
          let dailyRate = parseInt($("span#dailyRate").text());
          if (totalDays >= 30) {
            let monthlyRate = parseInt($("span#monthlyRate").text());
            let months = Math.round(totalDays / 30);
            let days = totalDays % 30;
            total += days * dailyRate + months * monthlyRate;
          } else {
            total += dailyRate * totalDays;
          }
          $("#driverCheck").removeAttr("disabled");
          // checking out
          if ($("#driverCheck").is(":checked")) {
            $("#driverCheck").prop("checked", false);
          }
        }
        $("span#total").text(total);
      } else {
        alert("invalid date selection, please check again");
        cookieTable.driver = null;
        $(".datepicker").val("");
        $("span#total").text("");
        $("driver-details span").each(function () {
          $(this).text("");
        });
        $("#driverCheck").attr("disabled", true);
        $("#driverCheck").prop("checked", false);
      }
    }
  }

  // add a driver randomly
  $("input#driverCheck").change(function () {
    if ($(this).is(":checked")) {
      // get a driver randomly
      date1 = $("#datepicker2").val();
      date2 = $("#datepicker1").val();
      $.ajax({
        url: baseURL + "driver/findRandom",
        method: "get",
        data: "date1=" + date1 + "&date2=" + date2,
        dataType: "json",
        success: function (res) {
          // if driver is present
          let driver = res.data;
          $.cookie("driverAssigned", true, { path: "/" });
          cookieTable.driver = driver;
          $("img#driver-profile").attr(
            "src",
            `data:image/png;base64, ${driver.profile}`
          );
          $("#pills-driver").show();
          $("#nav-profile-tab").show();
          // setting values
          $("#driver-details span[id]").each(function () {
            $(this).text(driver[$(this).attr("id")]);
          });
          $("span#driverTotal").text(calDays() * 1000);
        },
        error: function () {
          $.cookie("driverAssigned", false, { path: "/" });
          cookieTable.driver = null;
          alert("Cannot assign a driver for following dates!");
          $("#driverCheck").prop("checked", false);
          $("#pills-driver").hide();
          $("#nav-profile-tab").hide();
        },
      });
    } else {
      $.cookie("driverAssigned", false, { path: "/" });
      // cookieTable.driver = null;
      // $("#driver-details span[id]").each(function () {
      //   $(this).text("");
      // });
      $("span#driverTotal").text(0);
      $("#pills-driver").hide();
      $("#nav-profile-tab").hide();
    }
  });

  // ------------------------------------booking---------------------------------------

  function clearBookingForm() {
    $("form#booking")[0].reset();
    $("#driverCheck").attr("disabled", true);

    $("#pills-driver").hide();
    $("span#total").text("");
    $("span#driverTotal").text("");
    $("img#driver-profile").attr("src", "");
  }
  function placeBooking(id) {
    // checking if the vehicle is available for selected dates
    data =
      "regNo=" +
      $("span#registrationNumber").text() +
      "&date1=" +
      $("#datepicker1").val() +
      "&date2=" +
      $("#datepicker2").val();
    $.ajax({
      url: baseURL + "car/isAvailable",
      method: "get",
      data: data,
      dataType: "json",
      success: function (res) {
        if (res.data) {
          var formData = new FormData();
          formData.append(
            "advancePayment",
            parseFloat($("span#advancePayment").text())
          );
          console.groupEnd($.cookie("driverAssigned"));
          if (cookieTable.driver != null) {
            // driver = cookieTable["driver"];
            // console.dir(driver);
            formData.append(
              "rent",
              parseFloat($("span#total").text()) +
                parseFloat($("span#driverTotal").text())
            );
            formData.append("driver", cookieTable.driver.username);
          } else {
            formData.append("driver", null);
            formData.append("rent", parseFloat($("span#total").text()));
          }
          // let id = await generateNextId();
          formData.append("isAccepted", false);
          formData.append("bookingId", id);

          formData.append(
            "rent",
            parseFloat($("span#total").text()) +
              parseFloat($("span#driverTotal").text())
          );
          formData.append("dueDateTime", $("#datepicker2").val());
          formData.append("currentDateTime", $("#datepicker1").val());
          formData.append("car", $("span#registrationNumber").text());

          formData.append("user", cookieTable.user.username);
          formData.append(
            "paymentConfirmation",
            $("#bookingAdvance")[0].files[0]
          );
          $.ajax({
            url: baseURL + "booking/place",
            method: "post",
            data: formData,
            dataType: "json",
            success: function (res) {
              alert(res.message);
              clearBookingForm();
              loadAllBookingsForUser(user.username);
            },
            error: function (error) {
              // var jsObject = JSON.parse(error.responseText);
              // alert(jsObject.message);
            },
            processData: false,
            contentType: false,
          });
        } else {
          alert("car cannot be booked for the folowing dates");
        }
      },
      error: function (error) {
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
    });
  }
  $("#placeBooking").click(generateNextId);

  function generateNextId() {
    $.ajax({
      url: baseURL + "booking/lastId",
      method: "get",
      dataType: "json",
      success: function (res) {
        let prevId = res.data;
        let nextId;
        if (prevId) {
          let [prefix, suffix] = prevId.split("-");
          val = parseInt(suffix);
          nextId = prefix + "-" + (val + 1);
        } else {
          nextId = "BK-1";
        }
        placeBooking(nextId);
      },
      error: function (error) {
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
    });
  }

  function calDays() {
    let startDay = new Date($("#datepicker1").val());
    let endDay = new Date($("#datepicker2").val());
    var millisBetween = startDay.getTime() - endDay.getTime();
    return (totalDays = Math.round(
      Math.abs(millisBetween / (1000 * 3600 * 24))
    ));
  }

  function loadPendingOrdersForCustomer() {}
});

// custom alert
class CustomAlert {
  constructor() {
    this.alert = function (message, title) {
      $("body").append(
        '<div id="dialogoverlay"></div><div style="z-index:1000;" id="dialogbox" class="slit-in-vertical"><div><div id="dialogboxhead"></div><div id="dialogboxbody"></div><div id="dialogboxfoot"></div></div></div>'
      );

      let dialogoverlay = $("#dialogoverlay");
      let dialogbox = $("#dialogbox");

      // let winH = window.innerHeight;
      let winH = $(window).innerHeight();
      // dialogoverlay.style.height = winH + "px";
      dialogoverlay.css("height", winH + "px");

      // dialogbox.style.top = "100px";
      dialogbox.css("top", "100px");

      // dialogoverlay.style.display = "block";
      dialogbox.css("display", "block");
      dialogoverlay.css("display", "block");
      // dialogbox.style.display = "block";

      // document.getElementById("dialogboxhead").style.display = "block";
      $("#dialogboxhead").css("display", "block");

      if (typeof title === "undefined") {
        // document.getElementById("dialogboxhead").style.display = "none";
        $("#dialogboxhead").css("display", "none");
      } else {
        // document.getElementById("dialogboxhead").innerHTML =
        //   '<i class="fa fa-exclamation-circle" aria-hidden="true"></i> ' +
        //   title;
        $("#dialogboxhead").html(
          '<i class="fa fa-exclamation-circle" aria-hidden="true"></i> ' + title
        );
      }
      // document.getElementById("dialogboxbody").innerHTML = message;
      $("#dialogboxbody").html(message);
      $("#dialogboxfoot").html(
        '<button class="pure-material-button-contained active" onclick="customAlert.ok()">OK</button>'
      );

      $(window).scrollTop(0);

      // document.getElementById("dialogboxfoot").innerHTML =
      //   '<button class="pure-material-button-contained active" onclick="customAlert.ok()">OK</button>';
    };

    this.ok = function () {
      $("#dialogbox").hide();
      $("#dialogoverlay").hide();
      // document.getElementById("dialogbox").style.display = "none";
      // document.getElementById("dialogoverlay").style.display = "none";
    };
  }
}

let customAlert = new CustomAlert();
