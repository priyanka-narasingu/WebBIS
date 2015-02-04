<%-- 
    Document   : TranscationDetailsEntry
    Created on : Jun 13, 2014, 12:34:36 AM
    Author     : Pinku
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trasaction</title>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
	<link rel="stylesheet" href="css/bootstrap-theme.css" type="text/css">
	<script src="js/main.js"></script>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>
    </head>
    <body>
	<div class="container">
         
	     <div class="form-group col-sm-3">
                <label>Part Number</label>
                <select class="form-control">
                  <option>1</option>
                  <option>2</option>
                  <option>3</option>
                  <option>4</option>
                  <option>5</option>
                </select>
              </div>
	    <div class="col-sm-3">
		<label>Transcation Number</label>
		<input class="form-control" type="text" name="txtTxn">
		<Span><label>Selling Price</label>
		    <input class="form-control" type="text" name="txtSP">
		</Span></div>
		<div class="col-sm-3">
		    <label>Quantity</label>
		    <input class="form-control" type="text" name="txtQty">
		    <span>
			<label>Total Price</label>
		    <input class="form-control" type="text" name="txtTotalPrice"></span></div>
		    <div class="form-group col-sm-3">
                <label>Unit Price</label>
                <select class="form-control">
                  <option>1</option>
                  <option>2</option>
                  <option>3</option>
                  <option>4</option>
                  <option>5</option>
                </select>
              </div>
</div>
    </body>
</html>
