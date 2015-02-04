$(function() {
    //Binding the search text box to listen to input-to enable button (>3 char)
    $("#search").bind("input", function(event) {
        event.preventDefault();
        setSearchBtnState();
    });

//$("#searchBtn").on("click", searchFor());
    $("#searchBtn").click(function(event) {
        event.preventDefault();
        searchFor();
    });
    
     $("#refreshBtn").click(function(event) {
        event.preventDefault();
        populate();
    });
});   
    
    populate();
    function setSearchBtnState(){
    
        if($("#search").val().length >3){
            $("#searchBtn").prop("disabled", false);
        }
        else{
            $("#searchBtn").prop("disabled", true);
        } 
    }

// to perform search by product 
function searchFor(){
    
    //returns search string and parse into an object to ready for JSON
    var searchString = $("#search").val();

        
    
        var promise = $.post("ReplenishStock", {search: searchString});
        //var promise = $.post("ReplenishStock", {search: searchString, searchByType: searchBy});
        promise.done(function(data){
            if (!data){
                return;
            }
            document.getElementById("replenishDiv").innerHTML="";
      
            populateTable(data);
        });
        
        
        promise.fail(function(){
            
            document.getElementById("replenishDiv").innerHTML="Cannot find " + searchBy + " " + searchString;
            
        });                   
} 

function populate(){

        $.ajax({
        url:"ReplenishStock",
        type: "GET",
        //data : JSON.stringify(queryFor),
        data: {search: null, searchByType: null},
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data){
            //alert("hello")
            
            populateTable(data);
        },
        failure: function(errMsg) {
            alert(errMsg);
        },
        error:function(e,xhr){
            if(e.status==417)
            {
                var ht=" <div class='alert alert-danger'> There are no products to replenish.</div>"
                document.getElementById("replenishDiv").innerHTML=ht;
            }
        }
    });       
}

//populates the table based on data returned from servlet
function populateTable(data) {
    // TODO: Implement code to generate table (via JQuery)
    
    
    
    var arrayOfHeaders = data["headers"];
    var arrayOfInventories = data["inventory"];
 //   $("#stock-table-div").text(JSON.stringify(arrayOfProducts));
    
    var $table = $("<table id='table' class='table'>");
 
    var tr = $('<tr/>');
    
    //populates the table headers
    for (var i = 0; i < arrayOfHeaders.length; i++) {
        tr.append("<th>" + arrayOfHeaders[i].header + "</th>");
        $table.append(tr);  
    }   
    
   
    document.getElementById("replenishDiv").innerHTML=""; //clears the previous table if any
    //console.log("clear productdiv");
    $("#replenishDiv").append($($table));
    
    //populates the table based on array of products that servlet returns
    
    var partNum = [];

    
    for (var i = 0; i < arrayOfInventories.length; i++) {
        var tr = $('<tr/>');
        
        partNum.push(arrayOfInventories[i].partnumber);
        
        tr.append("<td>" + arrayOfInventories[i].partnumber + "</td>");
        tr.append("<td>" + arrayOfInventories[i].unitprice + "</td>"); //new
        tr.append("<td style='padding-top:12px'><span class='label label-danger' style='font-size:100%'>" + arrayOfInventories[i].quantity + "</span></td>");
        tr.append("<td>" + arrayOfInventories[i].reorderpoint + "</td>");
        tr.append("<td>" + arrayOfInventories[i].minreorderqty + "</td>");
        tr.append("<td style='padding-top:12px'><span class='label label-info' style='font-size:100%'>" + arrayOfInventories[i].orderamount + "</span></td>");
        tr.append("<td><input type='button' class='btn btn-default' name='reorder' id='reorder' value='Order'/> </td>");
        
       $table.append(tr);
       
           $('#table input').click(function(e) {
            e.preventDefault();
            $('#reorderamount').val($(this).closest('tr').find('td:nth-child(6)').text());
            $('#partnumber').val($(this).closest('tr').find('td:nth-child(1)').text());
            reorderStock();
        });
    }    
  
}

function reorderStock()
{
    //alert($("#reorderamount").val());
    var promise = $.post("ReplenishStock",{partNo:$('#partnumber').val(),qty:$('#reorderamount').val()})
            .done(function(){
                populate();
    }).fail(function(){      
        alert('fail');
    });
}
    