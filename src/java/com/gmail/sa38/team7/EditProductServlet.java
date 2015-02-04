/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.ProductDAO;
import com.gmail.sa38.team7.model.Manufacturer;
import com.gmail.sa38.team7.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
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
@WebServlet("/EditProduct")
public class EditProductServlet extends HttpServlet {

    @EJB ProductDAO productDAO;
    @PersistenceContext EntityManager em;
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try{
            String partnumber = request.getParameter("partnumber"); 
            String description = request.getParameter("description"); 
            String manufacturerid = request.getParameter("manufacturerid"); 
            String unitprice = request.getParameter("unitprice");            
           
            
            String result = productDAO.editProduct(partnumber, description, manufacturerid, unitprice);            

            response.setStatus(HttpServletResponse.SC_OK);
            //response.setContentType("application/json");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();            
            writer.write(result);
            writer.flush();
        
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println(ioe.toString());
        }     
                
        
    }


}
