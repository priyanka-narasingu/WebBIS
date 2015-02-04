/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

       
       
$(function() {
    //Binding the search text box to listen to input-to enable button (>3 char)
    $("#search").bind("input", function(event) {
        event.preventDefault();
        setSearchBtnState();
    });
    
    //Binding the addProduct text boxes to listen to input to enable button (>3 char for productID and mfrID)
    //selecting all text boxes in addDiv (obsolete)    
    $('.addDiv input:text').bind("input", function(event) {
        event.preventDefault();
        setAddBtnState();
    });  
    
    //selecting all text boxes in editDiv
    $('.editDiv input:text').bind("input", function(event) {
        event.preventDefault();
        setEditBtnState();
        setAddBtnState();
        setDelBtnState();
    });  
        
    
    //Sets the initial state

    setSearchBtnState();
    setAddBtnState(); 
    setEditBtnState();
    setDelBtnState();
    populate();    
    // TODO: Get fields from database    
    
    //listen to button clicks to perform actions
    $("#addProductBtn").click(function(event) {
        event.preventDefault();        
        addProduct();
    });
      
    //$("#searchBtn").on("click", searchFor());
    $("#searchBtn").click(function(event) {
        event.preventDefault();
        var searchStr = $("#search").val();
        var searchByType;
        if (document.getElementById('search_product').checked) {
            searchByType = "product";
        } else if (document.getElementById('search_manufacturer').checked) {
            searchByType = "manufacturer";
        }
                
        searchFor(searchStr, searchByType);
    });
    
        
    $("#search").keypress(function(event) {
        if (event.which === 13) {
            event.preventDefault();
            var searchStr = $("#search").val();
            var searchByType;
            if (document.getElementById('search_product').checked) {
                searchByType = "product";
            } else if (document.getElementById('search_manufacturer').checked) {
                searchByType = "manufacturer";
            }
            searchFor(searchStr, searchByType);
        }
    });
    
    // To select whole text when input field is selected -- to enable user to override quickly
    $('.editDiv input:text').click(function(event){
        event.preventDefault();
       $(this).select();        
    });
    
    $("#search").click(function(event){
        event.preventDefault();
       $(this).select();        
    });
    
    
    $("#refreshBtn").click(function(event) {
        event.preventDefault();
        $("#search").val("");
        populate();
    });
    
    $("#delProductBtn").click(function(event) {
        event.preventDefault();
        deleteProduct();
    });
    
    $("#editProductBtn").click(function(event) {
        event.preventDefault();
        editProduct();        
    });
    
    $("#clearBtn").click(function(event) {
        event.preventDefault();
        resetBtnStates();        
    });
    
    
    var t = $("#suggestionTemplate").html();
    var template = Handlebars.compile(t);
    
    $("#search").typeahead({
        highlight: true
    }, {
        name: "suggestionProductServlet",
        source: searchLookup,
        displayKey: "searchname",
        limit: 5,
        minLength: 2,
        templates: {
            header: "<b>Search</b>",
            suggestion: template
        }
    });
    
    $("#search").on("typeahead:selected", function() {
        $("#selected").text($(this).val());
    });
    
});

function searchLookup(query, callback) {
    
    if (document.getElementById('search_product').checked) {
        searchByType = "product";
    } else if (document.getElementById('search_manufacturer').checked) {
        searchByType = "manufacturer";
    }
    
    $.getJSON("suggestionProductServlet", {
        searchString: query, searchBy: searchByType
    }).done(function(result) {
        callback(result);
    });
}

//checks if search field is entered before enabling the searchBtn 
function setSearchBtnState(){
    
    if($("#search").val().length >0){
        $("#searchBtn").prop("disabled", false);
    }
    else{
        $("#searchBtn").prop("disabled", true);
    } 
}

//checks if all fields are entered before enabling the addProductBtn 
function setAddBtnState(){
    
    if($("#editpartnumber").val().length >3 && $("#editdescription").val().length >0 && $("#editmanufacturerid").val().length >3 
            && $("#editunitprice").val().length >0){
            
        $("#addProductBtn").prop("disabled", false);
    }
    else{
        
        $("#addProductBtn").prop("disabled", true);
    } 
}

//checks if all fields are entered before enabling the editProductBtn 
function setEditBtnState(){
    
    if($("#editpartnumber").val().length >3 && $("#editdescription").val().length >0 && $("#editmanufacturerid").val().length >3 && $("#editunitprice").val().length >0){
            
        $("#editProductBtn").prop("disabled", false);
    }
    else{
        
        $("#editProductBtn").prop("disabled", true);
    } 
}

function setDelBtnState(){
    if($("#editpartnumber").val().length >3 ){
            
        $("#delProductBtn").prop("disabled", false);
    }
    else{
        
        $("#delProductBtn").prop("disabled", true);
    }       
}
   
// to add new product
function addProduct(){
    var addPartNumber = $("#editpartnumber").val();
    var addDescription = $("#editdescription").val();
    var addManufacturerId = $("#editmanufacturerid").val();
    var addUnitPrice = $("#editunitprice").val();
    
    var promise = $.post("AddProduct", {partNumber: addPartNumber, description: addDescription, manufacturerid: addManufacturerId, unitprice: addUnitPrice});
    promise.done(function(data){
            if (!data){
                return;
            }
            
            if (data === "Product created"){
                
                // clear the text boxes and populate the added product
                document.getElementById("productDiv").innerHTML="";
                //populate(); 
                searchFor(addPartNumber, "productSpecific");
                alert(data); 
                resetBtnStates();
                
            }
            
//            else if (data ==="Product already exist"){
//                alert(data);                   
//            }
//            
//            else if (data ==="Manufacturer does not exist"){
//                alert(data);                   
//            }
            
            else{
                alert(data); 
            }                  
        
        });
            
        promise.fail(function(){
            
            document.getElementById("productDiv").innerHTML="Create product failed";
            
            //alert("Cannot find " + searchBy + " " + searchString);
        }); 
    
    
}

function editProduct(){
    
    var editPartNumber = $("#editpartnumber").val();
    var editDescription = $("#editdescription").val();
    var editManufacturerId = $("#editmanufacturerid").val();
    var editUnitPrice = $("#editunitprice").val();
   
    
    var promise = $.post("EditProduct", {partnumber: editPartNumber, description: editDescription, manufacturerid: editManufacturerId, unitprice: editUnitPrice});
    promise.done(function(data){
            if (!data){
                return;
            }
            
            if(data === "Product has been edited"){
                
                
                // clear the text boxes and populate the updated product
                //populate();
                document.getElementById("productDiv").innerHTML="";
                searchFor(editPartNumber, "productSpecific")
                alert(data); 
                
//                $("#editpartnumber").val("");
//                $("#editdescription").val("");
//                $("#editmanufacturerid").val("");
//                $("#editunitprice").val("");     

                resetBtnStates();               
                
            }
            else{
                //document.getElementById("productDiv").innerHTML="Product " + $("#editpartnumber").val() + " not found" ;
                //document.getElementById("productDiv").innerHTML=data ;
                alert(data); 
            }    
            
        });
            
        promise.fail(function(){
            
            document.getElementById("productDiv").innerHTML="Failed to edit product" ;
            
            //alert("Cannot find " + searchBy + " " + searchString);
        }); 
    
}

//delete product
function deleteProduct(){
    
    if (confirm("Are you sure you want to delete product " + $("#editpartnumber").val() + "?") === true) {   
    
    var deletePartNumber = $("#editpartnumber").val();
//    var editDescription = $("#editdescription").val();
//    var editManufacturerId = $("#editmanufacturerid").val();
//    var editUnitPrice = $("#editunitprice").val();
   
   
    
    //var promise = $.post("EditProduct", {partnumber: editPartNumber, description: editDescription, manufacturerid: editManufacturerId, unitprice: editUnitPrice});
    var promise = $.post("DeleteProduct", {partNumber: deletePartNumber});
    promise.done(function(data){
            if (!data){
                return;
            }
                        
            if (data ==="Product has been deleted"){              
            
                alert(data + ": " + deletePartNumber);   
                document.getElementById("productDiv").innerHTML="";
                // clear the text boxes and populate the updated product list
//                $("#editpartnumber").val("");
//                $("#editdescription").val("");
//                $("#editmanufacturerid").val("");
//                $("#editunitprice").val("");

                populate();         
                resetBtnStates();
                
            }
            
            else{
                alert(data); 
            }
            
            
        });
            
        promise.fail(function(){
            
            document.getElementById("productDiv").innerHTML="Delete product failed";
            
            //alert("Cannot find " + searchBy + " " + searchString);
        }); 
        
    } 
    
}


// to perform search by product or manufacturer
function searchFor(searchString, searchBy){
    
    //returns search string and parse into an object to ready for JSON
    //var searchString = $("#search").val();
//    var queryFor = new Object();
//    queryFor.searchString = searchString;
        
    //checks which radio button is selected
//    var searchBy;    
//    if(document.getElementById('search_product').checked) {
//        searchBy="product";  
//    }else if(document.getElementById('search_manufacturer').checked) {
//        searchBy="manufacturer"; 
//    }

        var promise = $.post("MainProductDetails", {search: searchString, searchByType: searchBy});
        promise.done(function(data){
            if (!data){
                return;
            }
            document.getElementById("productDiv").innerHTML="";
            
            //alert("Promise is done!");      
            populateTable(data);
        });
            
        promise.fail(function(){
            
            document.getElementById("productDiv").innerHTML="Cannot find " + searchBy + " " + searchString;
            
            //alert("Cannot find " + searchBy + " " + searchString);
        });                   
} 

// runs the populate function - sends ajax to servlet to obtain data
function populate(){
    
//    var searchString = $("#search").val();
//    var queryFor = new Object();
//    queryFor.searchString = searchString;
  
    
        $.ajax({
        url:"MainProductDetails",
        type: "POST",
        //data : JSON.stringify(queryFor),
        data: {search: null, searchByType: null},
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
            //alert("hello");
            populateTable(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });       
}

//populates the table based on data returned from servlet
function populateTable(data) {
    // TODO: Implement code to generate table (via JQuery)
           
    
    var arrayOfHeaders = data["headers"];
    var arrayOfProducts = data["products"];
 //   $("#stock-table-div").text(JSON.stringify(arrayOfProducts));
    
    var $table = $("<table class='table table-bordered table-striped col-sm-12'>");
    var $thead=$("<thead/>");
    var $tbody=$("<tbody/>")
    
    //$($table).prop("cellpadding", "5");
    //$($table).prop("border", "1");
    
    var tr = $('<tr/>');
    
    //populates the table headers
    
    for (var i = 0; i < arrayOfHeaders.length; i++) {
        tr.append("<th>" + arrayOfHeaders[i].header + "</th>");
        $thead.append(tr);
    }   
    $table.append($thead);
    
    document.getElementById("productDiv").innerHTML=""; //clears the previous table if any
    //console.log("clear productdiv");
    $("#productDiv").append($($table));
    
    //populates the table based on array of products that servlet returns
    
    var partNum = [];
    var desc = [];
    var mfrID =[];
    var mfrName=[];
    var unitP = [];
    
    for (var i = 0; i < arrayOfProducts.length; i++) {
        var tr = $('<tr/>');
        
        partNum.push(arrayOfProducts[i].partnumber);
        desc.push(arrayOfProducts[i].description);
        mfrID.push(arrayOfProducts[i].manufacturerid);
        mfrName.push(arrayOfProducts[i].manufacturername);
        unitP.push(arrayOfProducts[i].unitprice);
        
        tr.append("<td>" + arrayOfProducts[i].partnumber + "</td>");
        tr.append("<td>" + arrayOfProducts[i].description + "</td>");
        tr.append("<td>" + arrayOfProducts[i].manufacturerid + "</td>");
        tr.append("<td>" + arrayOfProducts[i].manufacturername + "</td>");
        tr.append("<td>" + arrayOfProducts[i].unitprice + "</td>");
                
        
        
        //adds the edit buttons to each row item
        var pNum = arrayOfProducts[i].partnumber;
        var $editBtn = $("<button class='btn btn-default'>");
        $($editBtn).text("Select for Edit");
        //$($editBtn).prop("id", "edit-" + pNum);
        
        $($editBtn).prop("id", i);
        
        // sets edit buttons to listen to clicks and logs the productID
        $($editBtn).on("click", function(event) {
           event.preventDefault();
           var myID = $(this).prop("id");
           console.log(myID);
           $("#editpartnumber").val(partNum[myID]);
           $("#editdescription").val(desc[myID]);
           $("#editmanufacturerid").val(mfrID[myID]);
           $("#editunitprice").val(unitP[myID]);     
           //window.location.hash="top";
           
           //enables the edit(save) button
           $("#editProductBtn").prop("disabled", false);
           $("#addProductBtn").prop("disabled", false);
           $("#delProductBtn").prop("disabled", false);
                     
        });
        tr.append($editBtn);        
        
//        tr.append("<td>" + "<button id='edit-" + arrayOfProducts[i].partnumber + "'>Edit</button>" +"</td>");
//        tr.append("<td>" + "<button id='delete-" + arrayOfProducts[i].partnumber + "'>Delete</button>"+"</td>");
                
        $tbody.append(tr);
        
        $table.append($tbody);
        
    }    
}


function resetBtnStates(){
    
    $("#editpartnumber").val("");
    $("#editdescription").val("");
    $("#editmanufacturerid").val("");
    $("#editunitprice").val("");
    
    $("#addProductBtn").prop("disabled", true);
    $("#editProductBtn").prop("disabled", true);
    $("#delProductBtn").prop("disabled", true);
}


