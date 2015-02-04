/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.TransactionDAO;
import com.gmail.sa38.team7.model.Transaction;
import com.gmail.sa38.team7.model.User;
import com.gmail.sa38.team7.utils.WebBISConstants;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author a0120502e
 */
@WebServlet("/EnquireTransactionHistorysss")
public class EnquireTransactionHistoryServlet extends HttpServlet {

    @EJB TransactionDAO transactionDAO;
//    @PersistenceContext EntityManager em;

    private Map<String, String> dict = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        JsonObject object;
        try (//         String searchString = request.getParameter("search");
//            String searchBy = request.getParameter("searchByType");
            JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream()))) {
            object = jsonReader.readObject();
        }

        
        String startDate = object.getString("StartDate");
        String endDate = object.getString("EndDate");
        String partNumber = object.getString("PartNumber");
            
        System.out.print(startDate + " " + endDate+" "+partNumber);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(WebBISConstants.USER);
        List<Transaction> transactionlist = 
                transactionDAO.getTransactionByPartNumber(user, partNumber, startDate, endDate);
        
        
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
    
//    
//    private void prepareDictionary() {
//       // instantiate dictionary
//        // key: sellingprice "0"
//        
//    }
//    
//    private String getTransactionValue(String key, Transaction T) {
//        if T.
//    }
//    
//}
