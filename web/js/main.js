/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// TODO: on load, need to ensure that the user is properly logged on, if not, redirect back to index.html

$(function() {
  var loginObject = new Object();
  loginObject.name = "fake";
  $.ajax({
    url:"Authentication",
    type: "POST",
    data : JSON.stringify(loginObject),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    async: false,
    success: function(data){
      authenticateResult(data);
    },
    failure: function(errMsg) {
      alert(errMsg);
    }
  });

  $("#sign-out-button").click(function(){
    $.ajax({
      url:"LogOut",
      type: "POST",
      data : "",
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      async: false,
      success: function(data){
        logOut(data);
      },
      failure: function(errMsg) {
        alert(errMsg);
      }
    });
  });
});

  function logOut(data) {
    var status = data['status'];
    console.log(status);
    if (status === 'SUCCESS') {
      window.location.href = "index.html";
    }
  }





  function authenticateResult(data) {

    var status = data['status'];
    console.log(status);
    var userRole = data['role'];
    console.log("userRole: " + userRole);
    if (status === 'SUCCESS' && userRole !== "") {
      var userName = data['name'];
      //        var userRole = data['role'];
      initHeader(userName, userRole);
      var divClerkArray = {
        "#product": "clerk-maintain-product.html",
        "#inventory": "clerk-maintain-inventory.jsp",
        "#replenish":"clerk-replenish-stock.html",
        "#transaction":"clerk-enquire-transaction.html"
      };
      var divMechanicArray = {
        "#stock": "mechanic-enquire-stock-summary.html",
        "#mech-transaction":"clerk-enquire-transaction.html",
        "#usage-details":"mechanic-enter-usage-detail.html"
      };
      var divArray;
      console.log("Logged in as " + userRole);
      if (userRole.toLowerCase() === 'clerk') {
	divArray = divClerkArray;
        $("#clerk-navigation-div").show();
        $("#mechanic-navigation-div").hide();
      } else if (userRole.toLowerCase() === 'mechanic') {
	divArray = divMechanicArray;
        $("#clerk-navigation-div").hide();
        $("#mechanic-navigation-div").show();
      } else {
        divArray = $.extend(divMechanicArray, divClerkArray); 
        $("#clerk-navigation-div").show();
        $("#mechanic-navigation-div").show();

      }

      for (var key in divArray) {
	$(key).on("click", function(event) {
	  event.preventDefault();
          var webpage = divArray["#" + $(this).get(0).id];
          /*console.log("This is my webpage: " + webpage);
            console.log(key + " ---- " + webpage);
            $("#work-frame").attr("src", webpage);*/
          window.location.href = webpage;
	});
      }//end for loop      

    } else {
      window.location.href = "index.html";
    }
  }

  function showAndTell(key) {
    console.log(key);
    $(".navigator").each(function() {
      var identity = "#" + $(this).get(0).id;
      var divisionID = "clerk-" + identity + "-div";
      if (identity === key) {
	$("#" + divisionID).show();
      } else {
	$("#" + divisionID).hide();
      }
    });
  }//end showAndTell

  function initHeader(userName, userRole) {
    $("#username").text(userName);
    $("#userrole").text(userRole);
  }
  function GetURLParameter(sParam)
  {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
      var sParameterName = sURLVariables[i].split('=');
      if (sParameterName[0] == sParam) {
        return sParameterName[1];
      }
    }
  }




