package com.gmail.sa38.team7.model;

import java.io.Serializable;

import java.lang.String;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public  class Product implements Serializable {


    @Column(name="unitprice",table="product",nullable=false)
    @Basic
    private double unitprice;


    @Column(name="partnumber",table="product",nullable=false,length=50)
    @Id
    private String partnumber;


    @ManyToOne(optional=false,targetEntity=Manufacturer.class)
    @JoinColumn(name="manufacturerid",referencedColumnName="manufacturerid",insertable=true,nullable=true,unique=false,updatable=true)
    private Manufacturer manufacturerid;


    @OneToOne(targetEntity=Inventory.class,mappedBy="product")
    private Inventory inventory;


    @Column(name="description",table="product",length=50)
    @Basic
    private String description;

    public Product(){

    }


   public double getUnitprice() {
        return this.unitprice;
    }


  public void setUnitprice (double unitprice) {
        this.unitprice = unitprice;
    }



   public String getPartnumber() {
        return this.partnumber;
    }


  public void setPartnumber (String partnumber) {
        this.partnumber = partnumber;
    }



   public Manufacturer getManufacturerid() {
        return this.manufacturerid;
    }


  public void setManufacturerid (Manufacturer manufacturerid) {
        this.manufacturerid = manufacturerid;
    }



   public Inventory getInventory() {
        return this.inventory;
    }


  public void setInventory (Inventory inventory) {
        this.inventory = inventory;
    }



   public String getDescription() {
        return this.description;
    }


  public void setDescription (String description) {
        this.description = description;
    }

}

