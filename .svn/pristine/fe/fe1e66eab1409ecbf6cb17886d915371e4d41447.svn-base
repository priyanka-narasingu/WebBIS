/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gmail.sa38.team7.models;

/**
 *
 * @author angus
 */

import org.apache.commons.lang3.StringUtils;


public class WebBISInventory {
    
    private String partNumber;
    private int quantity;
    private int reorderPoint;
    private int minReorderQty;
    private int orderAmount;
    private String shelfLocation;
    private int damagedItemCount;
    
    public WebBISInventory() {
        partNumber = "";
        quantity = -1;
        reorderPoint = -1;
        minReorderQty = -1;
        orderAmount = -1;
        shelfLocation = "";
        damagedItemCount = -1;
    }
    
    public WebBISInventory(String partNumber, int quantity, int reorderPoint, int minReorderQty, int orderAmount, String shelfLocation, int damagedItemCount) {
        this.partNumber = partNumber;
        this.quantity = quantity;
        this.reorderPoint = reorderPoint;
        this.minReorderQty = minReorderQty;
        this.orderAmount = orderAmount;
        this.shelfLocation = shelfLocation;
        this.damagedItemCount = damagedItemCount;
    }
    
    
    
    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(int reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public int getMinReorderQty() {
        return minReorderQty;
    }

    public void setMinReorderQty(int minReorderQty) {
        this.minReorderQty = minReorderQty;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public int getDamagedItemCount() {
        return damagedItemCount;
    }

    public void setDamagedItemCount(int damagedItemCount) {
        this.damagedItemCount = damagedItemCount;
    }

    @Override
    public String toString() {        
        String desc = partNumber + ", " + 
                StringUtils.join(", ", quantity, reorderPoint, minReorderQty, orderAmount, shelfLocation, damagedItemCount);
        return desc;
    }

}
