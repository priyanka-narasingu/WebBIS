/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {

    $("#search-btn").click(function( event ) {            
            event.preventDefault();
	    validatePartNumber();
            //searchForPartNumber();
        });
        
    $('#update-usage-btn').click(function( event ) {            
            event.preventDefault();
            validateCustomerID();
           
        });
        
});

function updateUsage() {
    var partNumber=$("#part-num-id").val();
    var quantity=$("#qty-id").val();
    var custID=$("#customer-id").val();
    var unitPrice=$("#unit-price-id").val();
    $("#total-price-id").val(unitPrice*quantity);    
   // var totalPrice=(unitPrice*quantity).toString();
    var usageObject = new Object();    
    usageObject.partNumber = partNumber;
    usageObject.quantity=quantity;
    usageObject.custID=custID;
    usageObject.unitPrice=unitPrice;
    //usageObject.totalPrice=$("#total-price-id").val(unitPrice*quantity);
    console.log(usageObject); 
     $.ajax({
        url:"EnterUsageDetailServlet",
        type: "POST",        
        data : JSON.stringify(usageObject),
        //data: {partNumber: partNumber, quantity: quantity, custID:custID, unitPrice: unitPrice, totalPrice:totalPrice },         
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
            showSuccessMessage();
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
		});
}

function searchForPartNumber() {
     var partNumber = $("#part-num-id").val();
    var jsonObject = new Object();    
    jsonObject.partNumber = partNumber;
     $.ajax({
        url:"SearchPartNumber",
        type: "POST",
        data : JSON.stringify(jsonObject),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
            populateFields(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });
}

function populateFields(data) {
    var unitPrice = data['unitprice'];
    $('#unit-price-id').val(unitPrice);
    
    
}
function showSuccessMessage(){
    alert("Transaction Updated");
}


function validateCustomerID()
{
    var custid=$('#customer-id').val().trim();
    var promise=$.post("ValidateCustomerID", {customerid:custid});
    promise.done(function(data){
	if(!data)
	{
	    return;
	}
	if(data==="correct")
	{
             updateUsage();
	}
	else if(data==="Invalid Customer ID")
	{
	    alert(data); 
	}
    });
}
function validatePartNumber()
{
    var prtnum=$('#part-num-id').val().trim();
    var promise=$.post("ValidatePartNumber", {partnumber:prtnum});
    promise.done(function(data){
	if(!data)
	{
	    return;
	}
	if(data==="correct")
	{
	    searchForPartNumber();
	}
	else if(data==="Invalid Part Number")
	{
	    alert(data); 
	}
    });
}

//function getUnitPrices() {
//        $.ajax({
//        url:"GetUnitPrices",
//        type: "POST",
//        data : "",
//        contentType: "application/json; charset=utf-8",
//        dataType: "json",
//        success: function(data){
//            populateUnitPrices(data);
//        },
//        failure: function(errMsg) {
//            alert(errMsg);
//        }
//    });
//}
//
//function populateUnitPrices(data) {
//    var array = data["unitPricesArray"];
//    $("#unit-price-id > option").remove();
//    $.each(array, function(key, datum) {
//        console.log("key" + key);
//        console.log("datum" + datum);
//        var price = datum["price"];        
//        $('#unit-price-id')
//          .append($('<option>', { value : key })
//          .text(price));        
//        console.log(price);
//    });
//    
//    
//    console.log(JSON.stringify(data));
//}