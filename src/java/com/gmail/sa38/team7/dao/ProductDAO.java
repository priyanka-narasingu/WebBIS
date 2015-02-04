/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.sa38.team7.dao;

import com.gmail.sa38.team7.model.Manufacturer;
import com.gmail.sa38.team7.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author a0122213a
 */
@Stateless
public class ProductDAO {

    @PersistenceContext
    EntityManager em;

    private List<Product> prodlist;

//    public void createProduct(Product product){       
//        em.persist(product);
//    }
    //public void createProduct(Product product){    
    public String addProduct(String partNumber, String description, String manufacturerid, String unitPrice) {

        String result = "";
        Boolean isNum = true;
        double unitPriceNum = 0;
        Boolean isPositiveNum = true;

        Product product = em.find(Product.class, partNumber);
        Manufacturer m = em.find(Manufacturer.class, manufacturerid);

        isNum = isNumber(unitPrice);
        
        if (isNum){
            isPositiveNum = isPositive(unitPrice);
        }

        try {
            if (product != null) {
                result = "Product already exist";
            }

            if (m == null) {
                result += "\nManufacturer does not exist-create manufacturer first";
            }

            if (!isNum) {
                result += "\nUnit price must be in number format";
            }
            
            if (!isPositiveNum) {
                result += "\nUnit price must be a positive number";
            }

            if (product == null && m != null && isNum && isPositiveNum) {
                Product p = new Product();
                p.setPartnumber(partNumber);
                p.setDescription(description);
                p.setManufacturerid(m);
                p.setUnitprice(Double.parseDouble(unitPrice));
                em.persist(p);

                result = "Product created";
            }
        } catch (PersistenceException pe) {
            System.out.println(pe.getMessage());
        }
        return result;
    }

    //public Boolean editProduct(Product product){   
    public String editProduct(String partnumber, String description, String manufacturerid, String unitPrice) {

        String result = "";
        Boolean isNum = true;
        Boolean isPositiveNum = true;

        Product p = em.find(Product.class, partnumber);
        Manufacturer m = em.find(Manufacturer.class, manufacturerid);

        isNum = isNumber(unitPrice);
        
        if (isNum){
            isPositiveNum = isPositive(unitPrice);
        }

        if (p == null) {
            result = "Product does not exist";
        }

        if (m == null) {
            result += "\nManufacturer does not exist-create manufacturer first";
        }

        if (!isNum) {
            result += "\nUnit price must be in number format";
        }
        
        if (!isPositiveNum) {
                result += "\nUnit price must be a positive number";
        }
        

        if (p != null && isNum && m != null && isPositiveNum) {

            p.setDescription(description);
            m.setManufacturerid(manufacturerid);
            p.setManufacturerid(m);
            p.setUnitprice(Double.parseDouble(unitPrice));

            em.merge(p);

            result = "Product has been edited";
        }

        return result;
    }

    public String deleteProduct(String partnumber) {

        String result = "";
        Product p = em.find(Product.class, partnumber);

        try {
            if (p != null) {
                em.remove(p);
                result = "Product has been deleted";
            } else {
                result = "Product " + partnumber + " not found";
            }
        } catch (PersistenceException pe) {
            System.out.println(pe.getMessage());
        }

        return result;
    }

    public Product getProductById(String partnumber) {

        Product p = em.find(Product.class, partnumber);

        return p;
    }
    
    public List getProductByIdLike(String partnumber) {

        Query q = em.createQuery("SELECT p FROM Product p WHERE p.partnumber LIKE :partnumber")
                .setParameter("partnumber", "%" + partnumber + "%");

        List result = q.getResultList();
        
        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }
        
    }
    
    public List getProductByNameLike(String descriptionString){              
        
        Query q = em.createQuery("SELECT p FROM Product p WHERE p.description LIKE :descriptionStr")
                .setParameter("descriptionStr", "%" + descriptionString + "%");
        
        List result = q.getResultList();
        
        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }
        
    }

    public List getManufacturerById(String manufacturerID) {

        Query q = em.createQuery("SELECT p FROM Product p WHERE p.manufacturerid.manufacturerid =:manufacturerid")
                .setParameter("manufacturerid", manufacturerID);

        List result = q.getResultList();

        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }
    }
    
        public List getManufacturerByIdLike(String manufacturerID) {

        Query q = em.createQuery("SELECT p FROM Product p WHERE p.manufacturerid.manufacturerid LIKE :manufacturerid")
                .setParameter("manufacturerid", "%" + manufacturerID + "%");

        List result = q.getResultList();

        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }
    }
    
    public List getManufacturerByNameLike(String manufacturerString) {

        Query q = em.createQuery("SELECT p FROM Product p WHERE p.manufacturerid.manufacturername LIKE :manufacturerStr")
                .setParameter("manufacturerStr", "%" + manufacturerString + "%");

        List result = q.getResultList();

        if (result.isEmpty()) {
            return null;
        } else {
            return result;
        }

    }
    
    

    public List getProducts() {

        System.out.println("getProducts");
        Query q = em.createQuery("select p from Product p");
        List<Product> productList = (List<Product>) q.getResultList();

        System.out.println(productList);

        return productList;
    }

    public static boolean isNumber(String string) {
        try {
            double d = Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isPositive(String string){
        
        boolean isPositiveNumber = true;
        double d = Double.parseDouble(string);
        
        if (d<0){
            isPositiveNumber = false;
        }        
        
        return isPositiveNumber;
    }
    

    public List<Product> getProductData(String a) {

        Query q = em.createQuery("SELECT p from Product p WHERE p.partnumber=:partno");
        q.setParameter("partno", a);
        prodlist = q.getResultList();
        return prodlist;
    }

    public List<Product> getProductDataByName(String a) {

        Query q = em.createQuery("SELECT p from Product p WHERE p.description LIKE :pname");
        q.setParameter("pname", a);
        prodlist = q.getResultList();
        return prodlist;
    }

    public void updatedata(String pn, String des) {
        Product p = em.find(Product.class, pn);
        p.setDescription(des);
        em.merge(p);
    }
      public List<Product> getProductDataAll() {

        Query q = em.createQuery("SELECT p from Product p ");
        prodlist = q.getResultList();
        
        Collections.sort(prodlist, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2){
                Double p1u = p1.getUnitprice();
                Double p2u = p2.getUnitprice();
                return p1u.compareTo(p2u);
            }
        });
        
        return prodlist;
    }
      public double getUnitPrice(String pn)
      {
          Product p = em.find(Product.class, pn);
          return p.getUnitprice();
      }
}
