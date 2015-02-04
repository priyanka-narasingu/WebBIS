/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7.utils;

/**
 *
 * @author angus
 */
public interface WebBISConstants {
    static String CLERK = "clerk";
    static String MECHANIC = "mechanic";
    static String LOGIN_FAILED = "failed";
    
    static String NAME_KEY = "username";
    static String PWD_KEY = "userpwd";
    
    static String ROLE = "role";
    static String USER = "user";
    
    static String JDBC_URL = "jdbc:mysql://localhost:3306/webbisdb";
    static String JDBC_USER = "root";
    static String JDBC_PWD = "mysql";
            
    static String [] STOCK_SUMMARY_HEADERS = {
        "Part #", "Description", "Quantity", "Reorder Point", 
        "Min Reorder Qty", "Order Amount", 
        "Shelf Location", "Damaged Item Count" };
    
    static String [] MAINTAIN_PRODUCT_DETAILS_HEADERS = {
        "Part #", "Description", "Manufacturer ID", "Manufacturer Name",
        "Unit Price"};
        
    //Thaw Tar
//    static String[] REPLENISH_STOCK_HEADERS = {
//    "Part Number", "Quantity", "Reorder Point", "Mininum Reorder Qty",
//    "Order Amount","Shelf Location", "Damaged Items Count"};
    
    static String[] REPLENISH_STOCK_HEADERS = {
    "Part #", "Unit Price", "Qty", "Reorder Point", "Min. Reorder Qty",
    "Order Amount",""};
    
    static String[] ENQUIRE_TRANSACTION_HISTROY = {
    "Transaction #", "Transaction Date", "User ID", "Part Number","Unit Price", "Selling Price",  
        "Quantity","Total Price", "Mfr / Consumer ID", "InOutFlag", "Manufacturer Flag", "Damaged Item"};     
}