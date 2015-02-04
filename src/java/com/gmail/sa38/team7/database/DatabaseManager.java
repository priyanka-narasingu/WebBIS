/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7.database;

import com.gmail.sa38.team7.model.Transaction;
import com.gmail.sa38.team7.model.User;
import com.gmail.sa38.team7.models.WebBISInventory;
import com.gmail.sa38.team7.utils.WebBISConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author A0120502E
 */
public class DatabaseManager {
    // This is the class that will handle connection with database.

    private Connection conn;

    public DatabaseManager() {
        boolean connection = getConnection();
        if (connection == false) {
            System.out.println("Error, unable to get connection");
        }
    }

    public List<Transaction> getTransactions(User user, String partnumber, Date sDate, Date eDate) {
        List<Transaction> result = new ArrayList<Transaction>();
        if (getConnection()) {
            try {

                java.sql.Date startDate = new java.sql.Date(sDate.getTime());
                java.sql.Date endDate = new java.sql.Date(eDate.getTime());

//                String selectSQL = "SELECT * FROM TRANSACTION WHERE PARTNUMBER=? AND TRANSACTIONDATE BETWEEN ? AND ?";
                String selectSQL = "SELECT * FROM TRANSACTION WHERE PARTNUMBER=?";

                PreparedStatement pstm = conn.prepareStatement(selectSQL);
                pstm.setString(1, partnumber);
//                pstm.setDate(2, startDate);
//                pstm.setDate(3, endDate);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Transaction trn = new Transaction();
                    trn.setTransactionnumber(rs.getInt("transactionnumber"));
                    
                    
                    
                    trn.setTransactiondate(rs.getDate("transactiondate"));
                    if (user == null) {
                        System.out.println("USER IS NULL");
                    }
                    trn.setUserid(user);
                    trn.setPartnumber(rs.getString("partnumber"));

                    Double uPrice = rs.getDouble("unitprice");
                    if (uPrice == null) {
                        uPrice = Double.parseDouble("0.0");
                    }
                    trn.setUnitprice(uPrice);
                    
                    
                    Double sPrice = rs.getDouble("sellingprice");
                    if (sPrice == null) {
                        sPrice = Double.parseDouble("0.0");
                    }
                    trn.setSellingprice(sPrice);
                    
                    Double tPrice = rs.getDouble("totalprice");
                    if (tPrice == null) {
                        tPrice = Double.parseDouble("0.0");
                    }
                    trn.setTotalprice(tPrice);
                    Integer qty = rs.getInt("quantity");
                    if (qty == null) {
                        qty = Integer.parseInt("0");
                    }
                    trn.setQuantity(qty);
                    trn.setManufacturerconsumerid(rs.getString("manufacturerconsumerid"));
                    trn.setInoutflag(rs.getInt("inoutflag"));
                    trn.setManufacturerflag(rs.getInt("manufacturerflag"));
                    trn.setDamageditemflag(rs.getInt("damageditemflag"));
                    
                    Date transactionDate = rs.getDate("transactiondate");
                    if ((startDate.before(transactionDate) || startDate.equals(transactionDate))
                            &&
                        (endDate.after(transactionDate) || endDate.equals(transactionDate)))
                    {
                        result.add(trn);
                    }
                }
                
                return result;
//                ResultSet rs = stm.executeQuery(selectSQL);
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }

        }
        return result;
    }

    public List<WebBISInventory> getInventory() {
        List<WebBISInventory> inventory = new ArrayList<WebBISInventory>();
        if (getConnection()) {
            try {
                String selectSQL = "SELECT * FROM INVENTORY";
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(selectSQL);
                while (rs.next()) {
                    String partNum = rs.getString("partnumber");
                    int quantity = rs.getInt("quantity");
                    int reorderPoint = rs.getInt("reorderpoint");
                    int minReorderQty = rs.getInt("minreorderqty");
                    int orderAmount = rs.getInt("orderamount");
                    String shelfLocation = rs.getString("shelflocation");
                    int damagedItemsCount = rs.getInt("damageditemscount");
                    WebBISInventory item = new WebBISInventory(partNum, quantity, reorderPoint, minReorderQty, orderAmount, shelfLocation, damagedItemsCount);
                    inventory.add(item);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return inventory;
    }

    // Example
    public User getCredentials(String name, String pwd) {
        User user = null;
        if (getConnection()) {
            try {
                String selectSQL = "SELECT USERROLE, USERID FROM USER WHERE USERNAME='"
                        + name + "' AND USERPASSWORD='" + pwd + "'";
                System.out.println(selectSQL);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(selectSQL);

                while (rs.next()) {
                    String role = rs.getString("USERROLE");
                    String id = rs.getString("USERID");
                    if (role == null || id == null) {
                        user = null;
                    } else {
                        user = new User();
                        user.setUsername(name);
                        user.setUserrole(role);
                        user.setUserid(rs.getString("USERID"));
                    }
                }
                return user;
//                }                
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                return null;
            } finally {
                try {
                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return null;
    }

    private boolean getConnection() {
        try {
            // load appropriate driver class
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Failed to load JDBC driver.");
        }
        try {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    return false;
                }
            }
            // construct an appropriate URL string            
            conn = DriverManager.getConnection(
                    WebBISConstants.JDBC_URL,
                    WebBISConstants.JDBC_USER,
                    WebBISConstants.JDBC_PWD);
            System.out.println("Connected.");
            return true;
            // close connection
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
        return false;
    }

}//end DatabaseManager
