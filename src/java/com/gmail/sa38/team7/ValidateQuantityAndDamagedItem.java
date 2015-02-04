/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.InventoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pinku
 */
@WebServlet(name = "ValidateQuantityAndDamagedItem", urlPatterns = {"/ValidateQuantityAndDamagedItem"})
public class ValidateQuantityAndDamagedItem extends HttpServlet {

@EJB InventoryDAO inventoryDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try{
            String damaged = request.getParameter("damaged"); 
            String quantity = request.getParameter("quantity"); 
            
            
	    String result=inventoryDAO.checkQuantityAndDamagedItem(quantity,damaged);
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
