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
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author monaung
 */

@Stateless
public class ReplenishStockDAO {
    
    @PersistenceContext private EntityManager em;
    
    public List<Inventory> getReplenishStock() {
        Query q = em.createQuery("SELECT i FROM Inventory i Where i.quantity < i.reorderpoint");
        List<Inventory> allInventory = q.getResultList();
        return allInventory;
    }
    
    public boolean reorderStock(Inventory i)
    {
        
        Query q=em.createQuery("Update Inventory i Set i.quantity=i.quantity + :quantity where i.partnumber=:partnumber")
                .setParameter("quantity", i.getQuantity())
                .setParameter("partnumber", i.getPartnumber());
        
        int e=q.executeUpdate();
        
        return e > 0;
    }
}
