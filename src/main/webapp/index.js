$(function () {
  $(".datepicker").datepicker();
  console.log("date updated");

  $("#tab-register").click(function () {
    $("#tab-login").removeClass("active");
    $(this).addClass("active");
    $("#pills-login").fadeOut();
    $("#pills-register").fadeIn();
    // setTimeout(() => {
    //   $("#pills-login").fadeOut();
    //   $("#pills-register").fadeIn();
    // }, 20);
  });

  $("#tab-login").click(function () {
    $("#tab-register").removeClass("active");
    $(this).addClass("active");
    $("#pills-register").fadeOut();
    $("#pills-login").fadeIn();
    // setTimeout(() => {
    //   $("#pills-register").fadeOut();
    //   $("#pills-login").fadeIn();
    // }, 20);
  });
});
