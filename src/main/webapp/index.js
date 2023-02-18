$(function () {
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

  $(".nav-link").each(
    $(this).click(function () {
      console.log("nav item clicked");
    })
  );
});
