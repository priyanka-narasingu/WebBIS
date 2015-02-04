package com.gmail.sa38.team7.model;

import java.io.Serializable;

import java.lang.Integer;
import java.lang.String;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="inventory")
public  class Inventory implements Serializable {


    @OneToOne(optional=false,targetEntity=Product.class)
    @JoinColumn(name="partnumber",referencedColumnName="partnumber",insertable=false,nullable=true,unique=false,updatable=false)
    private Product product;


    @Column(name="reorderpoint",table="inventory",nullable=false)
    @Basic
    private int reorderpoint;


    @Column(name="partnumber",table="inventory",nullable=false,length=50)
    @Id
    private String partnumber;


    @Column(name="shelflocation",table="inventory",length=50)
    @Basic
    private String shelflocation;


    @Column(name="damageditemscount",table="inventory")
    @Basic
    private Integer damageditemscount;


    @Column(name="minreorderqty",table="inventory",nullable=false)
    @Basic
    private int minreorderqty;


    @Column(name="quantity",table="inventory",nullable=false)
    @Basic
    private int quantity;


    @Column(name="orderamount",table="inventory",nullable=false)
    @Basic
    private int orderamount;

    public Inventory(){

    }


   public Product getProduct() {
        return this.product;
    }


  public void setProduct (Product product) {
        this.product = product;
    }



   public int getReorderpoint() {
        return this.reorderpoint;
    }


  public void setReorderpoint (int reorderpoint) {
        this.reorderpoint = reorderpoint;
    }



   public String getPartnumber() {
        return this.partnumber;
    }


  public void setPartnumber (String partnumber) {
        this.partnumber = partnumber;
    }



   public String getShelflocation() {
        return this.shelflocation;
    }


  public void setShelflocation (String shelflocation) {
        this.shelflocation = shelflocation;
    }



   public Integer getDamageditemscount() {
        return this.damageditemscount;
    }


  public void setDamageditemscount (Integer damageditemscount) {
        this.damageditemscount = damageditemscount;
    }



   public int getMinreorderqty() {
        return this.minreorderqty;
    }


  public void setMinreorderqty (int minreorderqty) {
        this.minreorderqty = minreorderqty;
    }



   public int getQuantity() {
        return this.quantity;
    }


  public void setQuantity (int quantity) {
        this.quantity = quantity;
    }



   public int getOrderamount() {
        return this.orderamount;
    }


  public void setOrderamount (int orderamount) {
        this.orderamount = orderamount;
    }

}

