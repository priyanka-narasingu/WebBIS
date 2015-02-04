/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.ProductDAO;
import com.gmail.sa38.team7.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a0122213a
 */
@WebServlet("/suggestionProductServlet")
public class SuggestionProductServlet extends HttpServlet {
    
    @EJB ProductDAO productDAO;       

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String query = request.getParameter("searchString");
        String searchBy = request.getParameter("searchBy");
        
        JsonArrayBuilder builder = Json.createArrayBuilder();
        if ((null != query) && (query.trim().length() > 0)) {
            query = query.toLowerCase();
        
            List<Product> productList = productDAO.getProducts();             
            
            Set s = new HashSet();
            
            for (Product p : productList) {
                if (searchBy.equals("product")){
                    if (p.getDescription().toLowerCase().contains(query)) {
                        s.add(p.getDescription());
                    }
                    if (p.getPartnumber().toLowerCase().contains(query)) {
                        s.add(p.getPartnumber());
                    }
                }             
                else if(searchBy.equals("manufacturer")){
                    if (p.getManufacturerid().getManufacturerid().toLowerCase().contains(query)) {
                        s.add(p.getManufacturerid().getManufacturerid());
                    }
                    if (p.getManufacturerid().getManufacturername().toLowerCase().contains(query)) {                    
                        s.add(p.getManufacturerid().getManufacturername());                    
                    }
                }      
            }
            
            Iterator iter = s.iterator();
            while (iter.hasNext()) {
                JsonObjectBuilder o = Json.createObjectBuilder();
                o.add("searchname", iter.next().toString());
                builder.add(o); 
            }        
        }
        
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.setContentType("application/json");
        
        try (JsonWriter w = Json.createWriter(response.getOutputStream())) {
            w.writeArray(builder.build());
        }
        
        
    }

    

}
