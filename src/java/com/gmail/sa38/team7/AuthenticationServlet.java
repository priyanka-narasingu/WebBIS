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
 * @author A0120502E
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/Authentication"})
public class AuthenticationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthenticationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenticationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            //String role = (String)session.getAttribute(WebBISConstants.ROLE);
            //WebBISUser user = (WebBISUser)session.getAttribute(WebBISConstants.USER);
            User user = (User)session.getAttribute(WebBISConstants.USER);
            String role = null;
            if (user != null) {
                role = user.getUserrole();
            }
            if (user == null || role == null) {                            
                try {
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("status", "FAILED");
                    JsonObject object = objectBuilder.build();
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String result = object.toString();
                    writer.write(result);
                    writer.flush();                    
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }                
            } else {
                try {
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("status", "SUCCESS");
                    objectBuilder.add("name", user.getUsername());
                    objectBuilder.add("role", role);
                    JsonObject object = objectBuilder.build();

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String result = object.toString();
                    writer.write(result);
                    writer.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }     
            }
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
