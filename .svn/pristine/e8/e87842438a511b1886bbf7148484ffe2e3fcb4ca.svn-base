/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.ProductDAO;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a0122213a
 */
@WebServlet(name = "SearchPartNumberServlet", urlPatterns = {"/SearchPartNumber"})
public class SearchPartNumberServlet extends HttpServlet {
@EJB ProductDAO productDAO;

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        String partNumber = object.getString("partNumber");
        double unitPrice = productDAO.getUnitPrice(partNumber);
        
        try {
        
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", "SUCCESS");
            objectBuilder.add("unitprice", unitPrice);   
            JsonObject unitPriceObj = objectBuilder.build();

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String result = unitPriceObj.toString();
            writer.write(result);
            writer.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }     

        

        
        
        
    }


}
