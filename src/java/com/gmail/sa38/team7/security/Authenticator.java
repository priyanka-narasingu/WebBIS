/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7.security;

import com.gmail.sa38.team7.database.DatabaseManager;
import com.gmail.sa38.team7.model.User;
import com.gmail.sa38.team7.utils.WebBISConstants;

/**
 *
 * @author angus
 */
public class Authenticator {
    
    private User user;
    
    public Authenticator() {
        super();
    }
    
    public User getUser() {
        return user;
    }
    
    public String checkCredentials(String name, String pwd) {                

        
        DatabaseManager dbmgr = new DatabaseManager();
        user = dbmgr.getCredentials(name, pwd);
        if (user == null) {
           return WebBISConstants.LOGIN_FAILED;
        } else {
            String role = user.getUserrole();
            System.out.println(name + " is authenticated with role: " + role);
            return role;
        }             
    }
    
    
}
