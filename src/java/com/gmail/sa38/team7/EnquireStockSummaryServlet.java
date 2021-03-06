/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.InventoryDAO;
import com.gmail.sa38.team7.model.Inventory;
import com.gmail.sa38.team7.model.Product;
import com.gmail.sa38.team7.utils.WebBISConstants;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a0120502e
 */
public class EnquireStockSummaryServlet extends HttpServlet {

    @EJB
    InventoryDAO inventoryDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        
        String searchType = object.getString("searchType");
        String searchString = object.getString("searchString"); 
        
        List<Inventory> inventory = null;
        if (searchType.equalsIgnoreCase("All")) {
            inventory = inventoryDAO.getAllInventoryData();
        } else {
           inventory = inventoryDAO.searchInventoryBy(searchType, searchString);
        }


        // Create a JSON Object to return to browser
        try {

            JsonObjectBuilder arrayObjectBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (int i = 0; i < WebBISConstants.STOCK_SUMMARY_HEADERS.length; i++) {
                arrayBuilder.add(arrayObjectBuilder.add("header", WebBISConstants.STOCK_SUMMARY_HEADERS[i]));
            }
            JsonArray headersArray = arrayBuilder.build();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

            objectBuilder.add("headers", headersArray);

            // Now we create an object of contents of inventory
            JsonArrayBuilder inventoryArray = Json.createArrayBuilder();
            for (Inventory inv : inventory) {
                Product prod = inv.getProduct();
                String description = prod.getDescription();
                JsonObjectBuilder objBuilder = Json.createObjectBuilder();
                objBuilder.add("partnum", inv.getPartnumber());
                objBuilder.add("description", description);
                objBuilder.add("quantity", inv.getQuantity());
                objBuilder.add("reorder", inv.getReorderpoint());
                objBuilder.add("minreorder", inv.getMinreorderqty());
                objBuilder.add("orderamt", inv.getOrderamount());
                objBuilder.add("shelfloc", inv.getShelflocation());
                objBuilder.add("damagecnt", inv.getDamageditemscount());
                JsonObject inventoryItem = objBuilder.build();
                inventoryArray.add(inventoryItem);
            }
            JsonArray inventoryArrayObj = inventoryArray.build();
            objectBuilder.add("inventories", inventoryArrayObj);

            JsonObject outputObject = objectBuilder.build();

            System.out.println(outputObject.toString());

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String result = outputObject.toString();
            writer.write(result);
            writer.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    }


