/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7.dao;

import com.gmail.sa38.team7.database.DatabaseManager;
import com.gmail.sa38.team7.model.Product;
import com.gmail.sa38.team7.model.Transaction;
import com.gmail.sa38.team7.model.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import com.gmail.sa38.team7.models.WebBISUser;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Pinku
 */
@Stateless
public class TransactionDAO {

   @PersistenceContext
    EntityManager em;
    //User u= new User();
    
    
    public void setTransactionDetails(String partNumber, String quantity, String custID,String unitPrice, User user)
    {
	try {
	//int pN=Integer.parseInt(partNumber);
	Product product=em.find(Product.class, partNumber);
	double qty=Double.parseDouble(quantity);
	double up=Double.parseDouble(unitPrice);
	double tp= up*qty;
                    
	Transaction transaction =new Transaction();
	transaction.setDamageditemflag(0);
	transaction.setInoutflag(0);
	transaction.setManufacturerflag(0);
	transaction.setManufacturerconsumerid(custID);
	transaction.setPartnumber(partNumber);
	transaction.setQuantity(Integer.parseInt(quantity));
	transaction.setSellingprice(null);
	transaction.setTotalprice(tp);
	transaction.setTransactiondate(new Date());
	transaction.setUnitprice(up);
	transaction.setUserid(user);
	em.persist(transaction);
	
        } catch(PersistenceException pe){
            System.out.println(pe.toString());
        }        	

    }
    
    public List<Transaction> getTransaction(){
        
        
        Query q = em.createQuery("Select t from Transaction t");
        List<Transaction> transactionList = (List<Transaction>) q.getResultList();
        
        return transactionList;
        
    }
        
     public List getTransactionByPartNumber(User user, String partnumber, String startDate, String endDate) {

         List<Transaction> result = new ArrayList<Transaction>();
         
         SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
         
         try {
            Date startingDate = dtf.parse(startDate);
            Calendar cStart = Calendar.getInstance();
            cStart.setTime(startingDate);

            Date endingDate = dtf.parse(endDate);
            Calendar cEnd = Calendar.getInstance();
            cEnd.setTime(endingDate);
            
            cStart.set(Calendar.HOUR, 0);
            cStart.set(Calendar.MINUTE, 0);
            cStart.set(Calendar.SECOND, 0);

            cEnd.set(Calendar.HOUR, 23);
            cEnd.set(Calendar.MINUTE, 59);
            cEnd.set(Calendar.SECOND, 59);
       
            
            System.out.println("starting date: " + cStart.getTime().toString());
            System.out.println("ending date: " + cEnd.getTime().toString());
            
            DatabaseManager mgr = new DatabaseManager();
            result = mgr.getTransactions(user, partnumber, cStart.getTime(), cEnd.getTime());
         } catch (ParseException pe ) {
             pe.printStackTrace();
         } 
         return result;
    }
}
    
    
    
