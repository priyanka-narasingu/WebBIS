package com.gmail.sa38.team7.model;

import java.io.Serializable;

import java.lang.String;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public  class Customer implements Serializable {


    @Column(name="customerid",table="customer",nullable=false,length=50)
    @Id
    private String customerid;


    @Column(name="customername",table="customer",nullable=false,length=50)
    @Basic
    private String customername;

    public Customer(){

    }


   public String getCustomerid() {
        return this.customerid;
    }


  public void setCustomerid (String customerid) {
        this.customerid = customerid;
    }



   public String getCustomername() {
        return this.customername;
    }


  public void setCustomername (String customername) {
        this.customername = customername;
    }

}

