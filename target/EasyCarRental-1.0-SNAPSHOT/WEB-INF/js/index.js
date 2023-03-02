$(function () {
  // UNDO
  // cookieTable.user = true;

  //  navigation
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
  }

  // for all nav links
  $(".navbar-main .nav-item").click(function () {
    let path = $(this).children("a").attr("href");
    console.log(path);
    changeActiveTab(path);
  });

  // console.log("date updated");

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
    //removes user
    // cookieTable.user = null;
    // $("#user-management-inner").show();
    // $("#logout").hide();
    // $("li#user").hide();
    location.reload();
  });

  let baseURL = "http://localhost:8080/EasyCarRental_war/";

  // ------------------------------------- user ----------------------------------------------

  // clear login form
  function clearLogin() {
    // $("form#userLogin :input").val("");
    $("form#userLogin")[0].reset();
  }

  function clearRegister() {
    $("form#userAdd")[0].reset();
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
        alert(res.message);
        if (res.data) {
          // adding cookies
          $.cookie("userLoggedIn", true, { path: "/" });
          // after logged in
          $("#user-management-inner").hide();
          $("#logout").show();
          $("li#user").show();
          loadDataForUserSection(uname);
        } else {
          alert("wrong credentials");
        }
      },
      error: function (error) {
        console.log(error);
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
      },
      error: function (error) {
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
    });
    // user = await $.get("user/findOne?username=" + uname);
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

    formData.append("file1", $("#inputGroupFile02")[0].files[0]);
    formData.append("file2", $("#inputGroupFile01")[0].files[0]);

    clearRegister();
    $.ajax({
      url: baseURL + "user/add",
      method: "post",
      data: formData,
      dataType: "json",
      success: function (res) {
        alert(res.message);
      },
      error: function (error) {
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
        alert(res.message);
        location.reload();
      },
      error: function (error) {
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
    });
  });

  function loadAllBookingsForUser(userId) {
    $.get(baseURL + "booking/");
  }

  // ------------------------------- car-single -------------------

  // load car cards
  function loadAllCarsForSelection() {
    $("#car-selection-row").empty();
    $.ajax({
      url: baseURL + "car/getAll",
      dataType: "json",
      success: function (resp) {
        console.log(resp.data);
        resp.data.forEach((car) => {
          let {
            brand,
            model,
            fuelType,
            monthlyRate,
            dailyRate,
            registrationNumber,
            img_front,
          } = car;
          $("#car-selection-row").append(
            '<div class="col-md-4">' +
              '<div class="car-wrap rounded ">' +
              '<div class="img rounded d-flex align-items-end" style="background-image: url(images/car-1.jpg);">' +
              `<img src=data:image/png;base64, ${img_front}></img>` +
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
        });
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

  loadAllCarsForSelection();
  // on date change
  function calculateValueOnDateChange(dateText) {
    let startDay = new Date($("#datepicker1").val());
    let endDay = new Date($("#datepicker2").val());
    let total = 0.0;
    try {
      var millisBetween = startDay.getTime() - endDay.getTime();
    } finally {
      let totalDays = Math.round(Math.abs(millisBetween / (1000 * 3600 * 24)));
      let dailyRate = parseInt($("span#dailyRate").text());
      if (totalDays >= 30) {
        let monthlyRate = parseInt($("span#monthlyRate").text());
        let months = Math.round(totalDays / 30);
        let days = totalDays % 30;
        total += days * dailyRate + months * monthlyRate;
      } else {
        total += dailyRate * totalDays;
      }
    }
    $("span#total").text(total);
  }
  // add a driver randomly
  $("input#driverCheck").change(function () {
    if ($(this).is(":checked")) {
      // get a driver randomly
      $.ajax({
        url: baseURL + "driver/findRandom",
        method: "get",
        dataType: "json",
        success: function (res) {
          let driver = res.data;
          $.cookie("driverAssigned", true, { path: "/" });
          cookieTable.driver = res.data;
          // setting values
          $("#driver-details span[id]").each(function () {
            $(this).text(driver[$(this).attr("id")]);
          });
        },
        error: function () {
          return null;
        },
      });
    } else {
      $.cookie("driverAssigned", false, { path: "/" });
      $.cookie("driver", null);
      $("#driver-details span[id]").each(function () {
        $(this).text("");
      });
    }
  });

  // ------------------------------------booking---------------------------------------

  function placeBooking(id) {
    var user,
      driver = {};
    if ($.cookie("userLoggedIn")) {
      user = cookieTable["user"];
      console.dir(user);
    }
    if ($.cookie("driverAssigned")) {
      driver = cookieTable["driver"];
      console.dir(driver);
    }
    var formData = new FormData();
    formData.append(
      "advancePayment",
      parseFloat($("span#advancePayment").text())
    );

    // let id = await generateNextId();
    formData.append("isAccepted", false);
    formData.append("bookingId", id);
    formData.append("rent", parseFloat($("span#total").text()));
    formData.append("dueDateTime", $("#datepicker2").val());
    formData.append("currentDateTime", $("#datepicker1").val());
    formData.append("car", $("span#registrationNumber").text());
    formData.append("driver", driver.username);
    formData.append("user", user.username);
    formData.append("paymentConfirmation", $("#bookingAdvance")[0].files[0]);
    $.ajax({
      url: baseURL + "booking/place",
      method: "post",
      data: formData,
      dataType: "json",
      success: function (res) {
        alert(res.message);
      },
      error: function (error) {
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
      processData: false,
      contentType: false,
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

  function loadPendingOrdersForCustomer() {}
});
