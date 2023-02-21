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

  // $(".sticky-top .nav-link").each(
  //   $(this).click(function () {
  //     console.log("nav item clicked");
  //   })
  // );

  // $(".boook-btn").each(
  //   $(this).click(function () {
  //     // to vehicle checkout
  //     console.log("vehicle checkout");
  //     $("#car-selection").addClass("collapse");
  //     $("#car-single-selection").removeClass("collapse");
  //   })
  // );

  let baseURL = "http://localhost:8080/EasyCarRental_war/";

  // save customer
  $("form#userAdd").submit(function (e) {
    e.preventDefault();
    var form = $("#userAdd").serializeArray(); // You need to use standard javascript object here
    var formData = new FormData();

    //Form data
    $.each(form, function (key, input) {
      formData.append(input.name, input.value);
    });
    // formData.append("user", form.serialize());

    // // var formData = $("#userAdd").serialize();
    // // will generate a query String including form data
    // console.log(form);

    // formData.append($("#userAdd")[0]);
    formData.append("file1", $("#inputGroupFile02")[0].files[0]);
    formData.append("file2", $("#inputGroupFile01")[0].files[0]);

    console.log(formData);

    //send ajax request to the customer servlet
    $.ajax({
      url: baseURL + "user/add",
      method: "post",
      data: formData,
      // mimeType: "multipart/form-data",
      dataType: "json",
      success: function (res) {
        alert(res.message);
        // loadAllCustomers();
      },
      error: function (error) {
        // var jsObject = JSON.parse(error.responseText);
        // alert(jsObject.message);
      },
      processData: false,
      contentType: false,
    });
  });
});
