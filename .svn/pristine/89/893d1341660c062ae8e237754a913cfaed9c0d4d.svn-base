/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7;

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
@WebServlet(name = "LogOutServlet", urlPatterns = {"/LogOut"})
public class LogOutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(WebBISConstants.USER);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("status", "SUCCESS");
        JsonObject object = objectBuilder.build();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        String result = object.toString();
        writer.write(result);
        writer.flush();

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
