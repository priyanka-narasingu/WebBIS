/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function() {
    
    
    $("#searchbydate").datepicker({inline:true});
    $("#searchbyEnddate").datepicker({inline:true});
    
    
    $("#search").bind("input", function(event) {
        event.preventDefault();
        setSearchBtnState();
    });
    $("#searchBtn").click(function(event) {
        event.preventDefault();
        sendDate();
        //populate();
        //alert("Hello");
        // pass a start and end date to the servlet
        // then work on the servlet 

    });
   
    setSearchBtnState();
    populate(); 
    
     
    $("#refreshBtn").click(function(event) {
        event.preventDefault();
        $("#search").val("");
        $("#searchbydate").val("");
         $("#searchbyEnddate").val("");
        
        populate();
    });
    
    $("#allBtn").click(function(event) {
        event.preventDefault();
        $("#search").val("");
        $("#searchbydate").val("");
         $("#searchbyEnddate").val("");
        
        // Search All
        $.ajax({
        url:"EnquireAllTransactions",
        type: "POST",
        data : "",
        contentType: "text/plain; charset=utf-8",
        dataType: "json",        
        success: function(data){
            populateTable(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
  });
    });
    

});


function sendDate(){
        
    var sDate = $("#searchbydate").val();
    var eDate = $("#searchbyEnddate").val();
    var part = $("#search").val();

    sDate = sDate.trim();
    eDate = eDate.trim();
    part = part.trim();

    console.log(sDate);
    console.log(eDate);
    console.log(part);
    
    var jsonObj = new Object();
    jsonObj.StartDate = sDate;
    jsonObj.EndDate = eDate;
    jsonObj.PartNumber = part;
    
    var test = JSON.stringify(jsonObj);
    console.log("here: " + test);
    
    $.ajax({
        url:"EnquireTransactionHistorysss",
        type: "POST",
        data : JSON.stringify(jsonObj),
        contentType: "application/json; charset=utf-8",
        dataType: "json",        
        success: function(data){
            populateTable(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
  });
 
}

    function populate() {
        $.ajax({
            url: "EnquireTransactionHistorysss",
            type: "POST",
            //data : JSON.stringify(queryFor),
            data: {search: null, searchByType: null},
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                //alert("hello");
                populateTable(data);
            },
            failure: function(errMsg) {
                alert(errMsg);
            }
        });
    }


function populateTable(data) {

    var arrayOfHeaders = data["headers"];
    var arrayOfTransaction = data["transaction"];
    
    //console.log(JSON.stringify(data));
    
    var $table = $("<table class='table table-bordered table-striped col-sm-12'>");
    var $thead = $("<thead/>");
    var $tbody = $("<tbody/>");

    //$($table).prop("cellpadding", "5");
    //$($table).prop("border", "1");

    var tr = $('<tr/>');

    //populates the table headers

    for (var i = 0; i < arrayOfHeaders.length; i++) {
        tr.append("<th>" + arrayOfHeaders[i].header + "</th>");
        $thead.append(tr);
    }
    $table.append($thead);

    document.getElementById("transactionDV").innerHTML = ""; //clears the previous table if any
    //console.log("clear productdiv");
    $("#transactionDV").append($($table));


    var tranNum = [];

    for (var i = 0; i < arrayOfTransaction.length; i++) {
        var tr = $('<tr/>');



        tranNum.push(arrayOfTransaction[i].transactionnumber);
        
        var transaction = arrayOfTransaction[i];
        var tnum = transaction.transactionnumber;
        tr.append("<td>" + tnum + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].transactiondate + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].userid + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].partnumber + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].unitprice + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].sellingprice + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].quantity + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].totalprice + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].manufacturerconsumerid + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].inoutflag + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].manufactureflag + "</td>");
        tr.append("<td>" + arrayOfTransaction[i].damageditemflag + "</td>");



        $tbody.append(tr);

        $table.append($tbody);

    }
    
    if (arrayOfTransaction.length <= 0) {
        alert("No records found, please try again.");
        $("#search").val("");

    }
    
    
}
 function setSearchBtnState(){
    
    if($("#search").val().length >0){
        $("#searchBtn").prop("disabled", false);
    }
    else{
        $("#searchBtn").prop("disabled", true);
    } 
}
