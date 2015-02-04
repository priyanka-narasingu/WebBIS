/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function() {
    
    $("#searchBtn").on("click",function(event) {
        event.preventDefault();    
	validatePartNumber();
	
	
    });    
    
    
    $('#update-btn').on("click",function(event) {
        event.preventDefault();        
	validateQuantityAndDamaged();
	console.log("update-btn clicked");
	if(damageditemFlag===true)
	{
	    update();
	    resetFelids();
	}    
    });
    
});

var damageditemFlag =false;


function update() {
    
    var updateObject = new Object();
    updateObject.functionality = 'updateInventory';
    
    updateObject.partNumber = $('#part-num-id').val().trim();
    updateObject.txtpartnumber = $('#part-num-id').val().trim();
    var orderAmt = $('#oa-id').val();
    //updateObject.txtorderamount = orderAmt.trim();
    updateObject.txtshelflocation = $('#sl-id').val().trim();
    updateObject.txtdamageditemscount = $('#dm-id').val().trim();
    updateObject.txtquantity = $('#qty-id').val().trim();

    $.ajax({
        url:"InventoryServlet",
        type: "POST",
        data : JSON.stringify(updateObject),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){            
            updateSuccessful(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });

}
function resetFelids()
{
    $('#sl-id').val("");
   $('#dm-id').val("");
   $('#qty-id').val("");
}

function updateSuccessful(data) {
    var updateStatus = data["results"];
    if(damageditemFlag===true)
    {
	
     alert("Update " + updateStatus);
     	searchInventory();
    }
    
}

function validateQuantityAndDamaged()
{
    
    var d=$('#dm-id').val().trim();
    var q= $('#qty-id').val().trim();
    var promise=$.post("ValidateQuantityAndDamagedItem", {damaged:d, quantity:q });
     promise.done(function(data){
	if(!data)
	{
	   // return;
	}
	if(data==="correct")
	{
	    damageditemFlag=true;
	}
	else if(data==="Damaged items cannot be greater than actual quantity")
	{
	    alert(data); 
	}
    });
}
function validatePartNumber()
{
    var prtnum=$('#searchPartNumber').val().trim();
    var promise=$.post("ValidatePartNumber", {partnumber:prtnum});
    promise.done(function(data){
	if(!data)
	{
	    return;
	}
	if(data==="correct")
	{
	    searchInventory();
	}
	else if(data==="Invalid Part Number")
	{
	    alert(data); 
	}
    });
}


function searchInventory() {
    
    var searchObject = new Object();    
    searchObject.functionality = 'searchPartNum';
    searchObject.partNumber = $("#searchPartNumber").val();        
    
    $.ajax({
        url:"InventoryServlet",
        type: "POST",
        data : JSON.stringify(searchObject),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
            populateInventoryTable(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        }
    });
    
    
    
    

}

function populateInventoryTable(data) {
    var array = data["results"];
    $("#inventory-table tr.item-row").remove(); // Clear any previous rows
    $.each(array, function(key, datum) {
	
        // Store part number in hidden field
        
	$('#part-num-id').val(datum["PN"]);
        
	
        var tr = $('<tr>');
        tr.addClass("item-row");
        
        
        var pn_td = $('<td>');
        pn_td.append(datum["PN"]);
        tr.append(pn_td);
        
        var desc_td = $('<td>');
        desc_td.append(datum["DESC"]);
        tr.append(desc_td);

        var qty_td = $('<td>');
        qty_td.append(datum["QTY"]);
        tr.append(qty_td);

        var oa=$('<td>');
	oa.append(datum["OA"]);
	tr.append(oa);
	
        var sl_td = $('<td>');
        sl_td.append(datum["SL"]);
        tr.append(sl_td);

        var dm_td = $('<td>');
        dm_td.append(datum["DM"]);
        tr.append(dm_td);                
        $('#inventory-table').append(tr);        
        
        $('#qty-id').val(datum["QTY"]);
        $('#oa-id').val(datum['OA']);
        $('#sl-id').val(datum['SL']);
        $('#dm-id').val(datum['DM']);        
    });
    
    
    
}
