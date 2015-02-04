/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7;
import com.gmail.sa38.team7.dao.ProductDAO;
import com.gmail.sa38.team7.model.Manufacturer;
import com.gmail.sa38.team7.model.Product;
import com.gmail.sa38.team7.utils.WebBISConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MainProductDetailsServlet extends HttpServlet {

    @EJB ProductDAO productDAO;
    @PersistenceContext EntityManager em;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
                       
        // Create a JSON Object to return to browser
        try {  

            String searchString = request.getParameter("search"); 
            String searchBy = request.getParameter("searchByType");

            
            JsonObjectBuilder singleObjectBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            
            for (int i = 0; i < WebBISConstants.MAINTAIN_PRODUCT_DETAILS_HEADERS.length; i++) {
                arrayBuilder.add(singleObjectBuilder.add("header", WebBISConstants.MAINTAIN_PRODUCT_DETAILS_HEADERS[i]));
            } 
            
            JsonArray headersArray = arrayBuilder.build();

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();          

            objectBuilder.add("headers", headersArray);   

           // List<Product> productList = null;
            
            List<Product> productList=new ArrayList<Product>();

            if (searchString == null){
                productList = productDAO.getProducts();
            }
            
//            else {
//                if (searchBy.equals("product")) {
//                    if (!(productDAO.getProductById(searchString) == null)) {
//                        productList.add(productDAO.getProductById(searchString));
//                    }
//                    if (!(productDAO.getProductByName(searchString) == null)) {
//                        productList.addAll(productDAO.getProductByName(searchString));
//                    }
//                } else if (searchBy.equals("manufacturer")) {
//                    if (!(productDAO.getManufacturerById(searchString) == null)) {
//                        productList = productDAO.getManufacturerById(searchString);
//                    }
//                    if (!(productDAO.getManufacturerByName(searchString) == null)) {
//                        productList.addAll(productDAO.getManufacturerByName(searchString));
//                    }
//
//                }
//            }
            
            else{
                if(searchBy.equals("product")){
                    if (!(productDAO.getProductByIdLike(searchString) == null)){
                        productList.addAll(productDAO.getProductByIdLike(searchString));
                    }                    
                    if (!(productDAO.getProductByNameLike(searchString) == null)){
                        productList.addAll(productDAO.getProductByNameLike(searchString));
                    }                    
                }
                else if(searchBy.equals("manufacturer")){
                    if (!(productDAO.getManufacturerByIdLike(searchString) == null)){
                    productList = productDAO.getManufacturerByIdLike(searchString);
                    }
                    if (!(productDAO.getManufacturerByNameLike(searchString) == null)){
                        productList.addAll(productDAO.getManufacturerByNameLike(searchString));
                    }  
                    
                }
                else if(searchBy.endsWith("productSpecific")){
                    if (!(productDAO.getProductById(searchString) == null)){
                        productList.add(productDAO.getProductById(searchString));
                    }    
                }
                
            }
            
            //System.out.print(productList);
            

            // Now we create an object of contents of products
            JsonArrayBuilder productArray = Json.createArrayBuilder();

            for (Product p: productList)
            {
                JsonObjectBuilder objBuilder = Json.createObjectBuilder();
                objBuilder.add("partnumber", p.getPartnumber());
                objBuilder.add("description", p.getDescription());
                //Manufacturer m = p.getManufacturerid();
                //String mid = m.getManufacturerid();
                //objBuilder.add("manufacturerid", mid);
                objBuilder.add("manufacturerid", p.getManufacturerid().getManufacturerid());
                
                //testing
                objBuilder.add("manufacturername", p.getManufacturerid().getManufacturername());
                objBuilder.add("unitprice", p.getUnitprice());

                JsonObject productItem = objBuilder.build();
                productArray.add(productItem);
            }

            JsonArray productArrayObj = productArray.build();
            objectBuilder.add("products", productArrayObj);

            JsonObject outputObject = objectBuilder.build();
            
            System.out.println(outputObject.toString());
  //            objectBuilder.add("status", "SUCCESS");
  //            objectBuilder.add("name", name);   
  //            objectBuilder.add("role", role);
  //            JsonObject object = objectBuilder.build();
  //
              response.setStatus(HttpServletResponse.SC_OK);
              response.setContentType("application/json");
              response.setCharacterEncoding("UTF-8");
              PrintWriter writer = response.getWriter();
              String result = outputObject.toString();
              writer.write(result);
              writer.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.toString());
        }     
    }


}
