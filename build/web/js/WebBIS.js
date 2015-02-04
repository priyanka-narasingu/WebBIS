/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$( function() {    // Clear text fields and disable button    
    
        // First, get session object
            $.ajax({
        url:"CheckLogin",
        type: "POST",
        data : "",
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
    
    
    
        $('#username').bind("input", function(event) {
            event.preventDefault();
            setLoginButtonState();
        });
        $('#userpwd').bind("input", function(event) {
            event.preventDefault();
            setLoginButtonState();
        });
            
        clearTextFields();
                       
        $("#loginbutton").click(function( event ) {
            
            event.preventDefault();
            login(event);
        });
        
        
    }//end function
); //end $(document).ready

function authenticateResult(data) {
    
    var status = data['login-status'];
    console.log(status);
    // If is logged in
    if (status === 'LOGGED-IN') {
        window.location.href = "main.html";
    } 
}



function login(event) {
    //alert( "Try to log in" );
    
    // Create json object to be sent from 
    // then send
    
    var loginObject = new Object();
    loginObject.name = $('#username').val().trim();
    loginObject.pwd = $('#userpwd').val();   

    $.ajax({
        url:"Login",
        type: "POST",
        data : JSON.stringify(loginObject),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
            loginResult(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });
    
}

function loginResult(data) {
    
    var status = data['status'];
    console.log(status);
    if (status === 'SUCCESS') {
     var name = data['name'];
     var role = data['role'];    
     //alert("You are logged in as " + role);
     
//    window.location.href = "main.html?username=" + name + "&userrole=" + role;     
      window.location.href = "main.html";
    } else {
     alert("Log in failed");
     clearTextFields();
    }

    

}



function setLoginButtonState() {
    var temp1 = $('#username').val().length;
    var temp2 = $('#userpwd').val().length;
    if (temp1 > 0 && 
        temp2 > 0) {
        $('#loginbutton').prop('disabled', false);
    } else {
        $('#loginbutton').prop('disabled', true);    
    }
}

function clearTextFields() {
    $('#username').val("");
    $('#userpwd').val("");
    setLoginButtonState();
}

