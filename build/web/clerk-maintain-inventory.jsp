    <%-- 
    Document   : InventoryDisplay
    Created on : Jun 11, 2014, 12:18:09 AM
    Author     : Pinku
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
	<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
	<link rel="stylesheet" href="css/bootstrap-theme.css" type="text/css">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
	<script>
	    function done()
	    {
		alert("Updated successfully");
	    }
	</script>
        <script src="js/main.js"></script>
      
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
    <script src="js/clerk-maintain-inventory.js"></script>	
    <div>
        <div>
            <div class="row">
                <div>
                    <div class="col-sm-4">
                         <input type="text" class="form-control" id="searchPartNumber" name="pn" placeholder="Part Number" autofocus="">
                    </div>
                    
                    <div class="col-sm-2">
                        <button class="btn btn-default" type="submit" id="searchBtn" name="search">
                            <span class="glyphicon glyphicon-search"></span>
                            Search
                        </button>
                    </div>
                    
                    <br/>
                    <br/>
                    <br/>

                    <div>
                        
                        <table id="inventory-table" class="table table-hover table-striped col-sm-12">
                                  
                            <thead>
                                <tr class="header-row">
                                    <th>Part #</th>
                                    <th>Product </th>
                                    <th>Quantity</th>
                                    <th>Order Amount</th>
                                    <th>Shelf Location</th>
                                    <th>Damaged Items Quantity</th>
                                </tr>
                            </thead>
			    
                        </table>
                    </div>
                    
                    <div class="col-sm-4">
                        <label>Quantity</label>
                        <input class="form-control" type="text" name="txtquantity" id="qty-id">
                    </div>
                    <div class="col-sm-4">
                        <label>Shelf Location</label>
                        <input class="form-control" type="text" name="txtshelflocation" id="sl-id">
                        <span>
                            <label>Damaged Items Quantity</label>
                        <input class="form-control" type="text" name="txtdamageditemscount" id="dm-id"></span>
			
                    <input class="form-control" type="hidden" name="txtpartnumber" id="part-num-id">
                    <br/>
                    <div class="pull-right">
                        <button class="btn btn-default" id="update-btn" name="update">Update</button>
                    </div>
                    </div>
                        
                    
		</div>
	    </div>
        </div>
    </div>
        <script type="text/javascript">
            $(function(){              
                $("#inventory").addClass("active");
            });
        </script>
        
    </body>

</html>
