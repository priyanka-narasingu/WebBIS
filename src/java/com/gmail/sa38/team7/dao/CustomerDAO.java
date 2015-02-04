/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7.dao;

import com.gmail.sa38.team7.model.Customer;
import com.gmail.sa38.team7.model.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pinku
 */
@Stateless
public class CustomerDAO {
@PersistenceContext
    EntityManager em;
    List<Customer> custList;
    Customer cust;
public List<Customer> getCustID(){

     Query q = em.createQuery("SELECT c FROM Customer c");
             custList = q.getResultList();
        return custList;
}

public String checkCustId(String c)
    {
	String result = "correct";
	try {
	     
	     cust=em.find(Customer.class, c);
	     if(cust==null)
	     {
		 result="Invalid Customer ID";
	     }
	     
	    return result ;
    }
    catch (Exception e) {
	    return result="Invalid Customer ID";
	}
    }


}
