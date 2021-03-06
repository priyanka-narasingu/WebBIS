/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.TransactionDAO;
import com.gmail.sa38.team7.model.Transaction;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author guedado
 */
@WebServlet(name = "EnquireAllTransactionsServlet", urlPatterns = {"/EnquireAllTransactions"})
public class EnquireAllTransactionsServlet extends HttpServlet {

    @EJB TransactionDAO transactionDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // We want all transactions
        List<Transaction> transactionlist = new ArrayList<Transaction>();
        
        transactionlist = transactionDAO.getTransaction();
        
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObjectBuilder arrayObjectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < WebBISConstants.ENQUIRE_TRANSACTION_HISTROY.length; i++) {
            arrayBuilder.add(arrayObjectBuilder.add("header", WebBISConstants.ENQUIRE_TRANSACTION_HISTROY[i]));
        }
        JsonArray headersArray = arrayBuilder.build();
        objectBuilder.add("headers", headersArray);    
        
        JsonArrayBuilder transactionArray = Json.createArrayBuilder();
        for (Transaction T : transactionlist) {
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            
            //String transNumber = getTransactionValue("transactionnumber");
            
            objBuilder.add("transactionnumber", T.getTransactionnumber());
            objBuilder.add("transactiondate", T.getTransactiondate().toString());
            objBuilder.add("userid", T.getUserid().getUserid());
            objBuilder.add("partnumber", T.getPartnumber());
            objBuilder.add("unitprice", T.getUnitprice());

            if (T.getSellingprice() == null) {
                objBuilder.add("sellingprice", "-");
            } else {
                objBuilder.add("sellingprice", T.getSellingprice());
            }

            objBuilder.add("quantity", T.getQuantity());
            
            if (T.getSellingprice() == null) {
                objBuilder.add("totalprice", "-");
            } else {
                objBuilder.add("totalprice", T.getTotalprice());
            }
            objBuilder.add("manufacturerconsumerid", T.getManufacturerconsumerid());
            objBuilder.add("inoutflag", T.getInoutflag());
            objBuilder.add("manufactureflag", T.getManufacturerflag());
            objBuilder.add("damageditemflag", T.getDamageditemflag());

            transactionArray.add(objBuilder);

        }
        JsonArray transactionArrayObj = transactionArray.build();
        System.out.println(transactionArrayObj.toString());
        objectBuilder.add("transaction", transactionArrayObj);

        JsonObject outputObject = objectBuilder.build();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String result = outputObject.toString();        
        System.out.println(result);
        writer.write(result);        
        writer.flush();
        
        
        
        
  
    }

}
