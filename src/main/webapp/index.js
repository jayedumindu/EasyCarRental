$(function () {
  $(".datepicker").datepicker();
  console.log("date updated");

  $("#tab-register").click(function () {
    $("#tab-login").removeClass("active");
    $(this).addClass("active");
    $("#pills-login").fadeOut();
    setTimeout(() => {
      $("#pills-register").fadeIn();
    }, 50);
    console.log("this runs");
    // $("#pills-login").removeClass("show active");
    // setTimeout(() => {
    //   $("#pills-register").addClass("show active");
    // }, 500);
    // console.log("this runs");
  });

  $("#tab-login").click(function () {
    $("#tab-register").removeClass("active");
    $(this).addClass("active");
    $("#pills-register").fadeOut();
    setTimeout(() => {
      $("#pills-login").fadeIn();
    }, 50);
    // $("#pills-register").removeClass("show active");
    // setTimeout(() => {
    //   $("#pills-login").addClass("show active");
    // }, 500);
  });
});
