/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7;

import com.gmail.sa38.team7.dao.ProductDAO;
import com.gmail.sa38.team7.dao.TransactionDAO;
import com.gmail.sa38.team7.model.Product;
import com.gmail.sa38.team7.model.User;
//import com.gmail.sa38.team7.models.WebBISUser;
import com.gmail.sa38.team7.utils.WebBISConstants;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/EnterUsageDetailServlet"})
public class EnterUsageDetailServlet extends HttpServlet {

    @EJB
    TransactionDAO transcationDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user from session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(WebBISConstants.USER);
        String userid = null;
        if (user != null) {
            userid = user.getUserid();
        }

        JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        String partNumber = object.getString("partNumber");
        String quantity = object.getString("quantity");
        String custID = object.getString("custID");
        String unitPrice = object.getString("unitPrice");

        transcationDAO.setTransactionDetails(partNumber, quantity, custID, unitPrice, user);

        try {

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", "SUCCESS");
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
