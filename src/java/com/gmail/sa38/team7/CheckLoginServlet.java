/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7;

import com.gmail.sa38.team7.model.User;
import com.gmail.sa38.team7.utils.WebBISConstants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guedado
 */
@WebServlet(name = "CheckLoginServlet", urlPatterns = {"/CheckLogin"})
public class CheckLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        //WebBISUser user = (WebBISUser) session.getAttribute(WebBISConstants.USER);
        User user = (User) session.getAttribute(WebBISConstants.USER);
        
        String result = "NOT_LOGGED_IN";
        if (user != null) {
            result = "LOGGED-IN";
        }

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("login-status", result);
        JsonObject object = objectBuilder.build();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String returnResult = object.toString();
        writer.write(returnResult);
        writer.flush();

    }

}
