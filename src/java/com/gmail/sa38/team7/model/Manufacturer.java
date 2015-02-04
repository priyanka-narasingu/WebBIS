package com.gmail.sa38.team7.model;

import java.io.Serializable;

import java.lang.String;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="manufacturer")
public  class Manufacturer implements Serializable {


    @Column(name="manufacturerid",table="manufacturer",nullable=false,length=50)
    @Id
    private String manufacturerid;


    @Column(name="manufacturername",table="manufacturer",nullable=false,length=50)
    @Basic
    private String manufacturername;


    @OneToMany(targetEntity=Product.class,mappedBy="manufacturerid")
    private Collection<Product> productCollection;

    public Manufacturer(){

    }


   public String getManufacturerid() {
        return this.manufacturerid;
    }


  public void setManufacturerid (String manufacturerid) {
        this.manufacturerid = manufacturerid;
    }



   public String getManufacturername() {
        return this.manufacturername;
    }


  public void setManufacturername (String manufacturername) {
        this.manufacturername = manufacturername;
    }



   public Collection<Product> getProductCollection() {
        return this.productCollection;
    }


  public void setProductCollection (Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

}

