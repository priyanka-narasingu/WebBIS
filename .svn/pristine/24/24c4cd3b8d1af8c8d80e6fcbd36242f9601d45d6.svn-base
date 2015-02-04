/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {

    prepareWidgets();

    // Enquire with criteria
    $("#search-by-btn").on("click", function() {
        
        // Get search string & type
        var searchType = $("#summary-search-type option:selected").text();
        var searchTextField = $("#summary-search-string").val();
        
        var jsonObject = new Object();
        jsonObject.searchType = searchType;
        jsonObject.searchString = searchTextField;
        
      $.ajax({
            url: "EnquireStockSummary",
            type: "POST",
                data : JSON.stringify(jsonObject),
    contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                stockSummary(data);
            },
            failure: function(errMsg) {
                alert(errMsg);
            }
        }); 
    });
    
    
    // List all stock
    $("#enquire-stock-btn").on("click", function() {
        var jsonObject = new Object();
        jsonObject.searchType = "All";
        jsonObject.searchString = "";
        $.ajax({
            url: "EnquireStockSummary",
            type: "POST",
                data : JSON.stringify(jsonObject),
    contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                stockSummary(data);
            },
            failure: function(errMsg) {
                alert(errMsg);
            }
        });

    });

});


function prepareWidgets() {
    console.log("prepareWidgets");
    //var searchTextField = $("#summary-search-string");
    var searchOption = $("#summary-search-type option:selected").text();
    console.log("initial selected: " + searchOption);
    
    var dropdown = $("#summary-search-type");
    dropdown.change(function() {
       var selected = $(this).text(); 
       console.log("Selection changed: " + selected);
    });
}


function stockSummary(data) {
    
    //var info = JSON.stringify(data);
    //$("#stock-table-div").text(info);
    
    var arrayOfHeaders = data["headers"];
    var arrayOfInventories = data["inventories"];
    //$("#stock-table-div").text(JSON.stringify(arrayOfInventories));
    
    
    var $table = $("<table class='table table-bordered table-striped col-sm-12'>");
    var tr = $('<tr/>');
    for (var i = 0; i < arrayOfHeaders.length; i++) {
        tr.append("<th>" + arrayOfHeaders[i].header + "</th>");
        $table.append(tr);
    }   
    document.getElementById("stock-table-div").innerHTML=""; //clears the previous table if any
    $("#stock-table-div").append($($table));
    
    for (var i = 0; i < arrayOfInventories.length; i++) {
        var tr = $('<tr/>');
        tr.append("<td>" + arrayOfInventories[i].partnum + "</td>");
        tr.append("<td>" + arrayOfInventories[i].description + "</td>");
        tr.append("<td>" + arrayOfInventories[i].quantity + "</td>");
        tr.append("<td>" + arrayOfInventories[i].reorder + "</td>");
        tr.append("<td>" + arrayOfInventories[i].minreorder + "</td>");
        tr.append("<td>" + arrayOfInventories[i].orderamt + "</td>");
        tr.append("<td>" + arrayOfInventories[i].shelfloc + "</td>");
        tr.append("<td>" + arrayOfInventories[i].damagecnt + "</td>");
        $table.append(tr);
    }
}//end stockSummary