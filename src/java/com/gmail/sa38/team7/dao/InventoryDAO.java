/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7.dao;


import com.gmail.sa38.team7.model.Inventory;
import com.gmail.sa38.team7.model.Product;
import com.gmail.sa38.team7.model.Transaction;
import com.gmail.sa38.team7.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pinku
 */
@Stateless
public class InventoryDAO {

    @PersistenceContext
    EntityManager em;
    List<Inventory> list;
    Product product;
    Transaction transaction;
    Inventory inventory;
   
    public String checkPartNumber(String p)
    {
	String result = "correct";
	try {
	     
	     product=em.find(Product.class, p);
	     if(product==null)
	     {
		 result="Invalid Part Number";
	     }
	     
	    return result ;
    }
    catch (Exception e) {
	    return result="Invalid Part Number";
	}
    }
    public String checkQuantityAndDamagedItem(String q, String d )
    {
	String result="correct";
	int qty=Integer.parseInt(q);
	int dmg=Integer.parseInt(d);
	if(dmg>qty)
	{
	    result="Damaged items cannot be greater than actual quantity";
	}
	
	return result;
	
    }
   
    
    public List<Inventory> getInventoryData(String s) {
	 
        Query q = em.createQuery("SELECT i FROM Inventory i WHERE i.partnumber=:pn").setParameter("pn", s);
        list = q.getResultList();
        return list;
    }

    public void setNewInventory(Inventory invt) {
        em.persist(invt);

    }
    
    public void updateInventory(String p, int d,String shelfLocation, int quantity,User user) {
         inventory= em.find(Inventory.class, p);
	product=em.find(Product.class, p);
	transaction=new Transaction();
	int qty=0;
        if (d != list.get(0).getDamageditemscount()) {
	    qty=quantity-d;
            inventory.setQuantity(qty);
	    transaction.setDamageditemflag(1);
	    transaction.setManufacturerflag(1);
	    transaction.setInoutflag(0);
            transaction.setUserid(user);
        } else {
	    qty=quantity;
            inventory.setQuantity(quantity);
	    transaction.setDamageditemflag(0);
	    transaction.setManufacturerflag(0);
	    transaction.setInoutflag(1);
            transaction.setUserid(user);
        }
	
        
        
        inventory.setDamageditemscount(d + list.get(0).getDamageditemscount());
        inventory.setShelflocation(shelfLocation);
	
        em.merge(inventory);
	updateTranscationRecord(qty,user);
	
    }
    public void updateTranscationRecord(int quantity,User user)
    {
	//User u = em.find(User.class, "S0001");
	
	
	transaction.setManufacturerconsumerid(product.getManufacturerid().getManufacturerid());
	transaction.setPartnumber(inventory.getPartnumber());
	transaction.setQuantity(quantity);
	transaction.setSellingprice(null);
	transaction.setTotalprice(null);
	transaction.setTransactiondate(new Date());
	transaction.setUnitprice(product.getUnitprice());
	transaction.setUserid(user);
	em.persist(transaction);
	
    }

    public List<Inventory> getAllInventoryData() {
        Query q = em.createQuery("SELECT i FROM Inventory i");
        List<Inventory> allInventory = q.getResultList();
        return allInventory;
    }
    
    public List<Inventory> searchInventoryBy(String searchType, String searchString) {
        
        Map<String, String> dict = buildDictionary();
        String queryKey = dict.get(searchType);
        Query q = em.createQuery("SELECT i FROM Inventory i WHERE i." + queryKey + " LIKE :key").setParameter("key", '%' + searchString + '%');
        
        
//        Query q = em.createQuery("SELECT i FROM Inventory i WHERE " + queryKey + "=" + searchString);
        List<Inventory> invent = q.getResultList();
        return invent;
    }
    
    private Map<String, String> buildDictionary() {
        Map<String, String> dictionary = new HashMap<String, String>();
        dictionary.put("Part Number", "partnumber");
        dictionary.put("Shelf Location", "shelflocation");
        return dictionary;
    }

}
