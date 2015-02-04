/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7;

import com.gmail.sa38.team7.model.User;
import com.gmail.sa38.team7.security.Authenticator;
import com.gmail.sa38.team7.utils.WebBISConstants;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author angus
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        // Pre-condition: name and password parameters exist in request parameters
        // Postconditions: 
        // (1) login failed
        // (2) login succeed as Clerk
        // (3) login succeed as Mechanic
        // 1. get received JSON data from request
        
        JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream()));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        
        String name = object.getString("name");
        String pwd = object.getString("pwd");
        
        // Get name and password and check with database to authenticate
        //String name = request.getParameter(WebBISConstants.NAME_KEY);
        //String pwd = request.getParameter(WebBISConstants.PWD_KEY);
        Authenticator auth = new Authenticator();
        String credentials = auth.checkCredentials(name, pwd);
        
        
        HttpSession session = request.getSession();
        if (session != null && credentials != null) {
            if (credentials.equalsIgnoreCase(WebBISConstants.LOGIN_FAILED)) {
                doLoginFailed(response);
            } else {
                User user = auth.getUser();   
                if (user == null) {
                    doLoginFailed(response);
                } else {
                session.removeAttribute(WebBISConstants.USER);
                session.setAttribute(WebBISConstants.USER, user);
                String role = WebBISConstants.CLERK;
                if (credentials.equalsIgnoreCase(WebBISConstants.CLERK)) {            
                    session.removeAttribute(WebBISConstants.ROLE);
                    session.setAttribute(WebBISConstants.ROLE, WebBISConstants.CLERK);                            
                } else if (credentials.equalsIgnoreCase(WebBISConstants.MECHANIC)) {            
                    session.removeAttribute(WebBISConstants.ROLE);
                    session.setAttribute(WebBISConstants.ROLE, WebBISConstants.CLERK);            
                    role = WebBISConstants.MECHANIC;
                }
                doLoginSucceed(response, role, name);
                }
            }
        } else {
            doLoginFailed(response);
        }
    }

    private void doLoginSucceed(HttpServletResponse response, String role, String name) {
        // Create JSON reply with information
      
        try {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", "SUCCESS");
            objectBuilder.add("name", name);   
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
    
    private void doLoginFailed(HttpServletResponse response) {
        // Create JSON reply with failed login               
        try {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", "FAIL");
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
