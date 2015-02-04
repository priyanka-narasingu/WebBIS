package com.gmail.sa38.team7.model;

import java.io.Serializable;

import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="transaction")
public  class Transaction implements Serializable {


    @Column(name="manufacturerconsumerid",table="transaction",nullable=false,length=50)
    @Basic
    private String manufacturerconsumerid;


    @Column(name="inoutflag",table="transaction",nullable=false)
    @Basic
    private int inoutflag;


    @Column(name="transactiondate",table="transaction",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    private Date transactiondate;


    @Column(name="damageditemflag",table="transaction",nullable=false)
    @Basic
    private int damageditemflag;


    @Column(name="unitprice",table="transaction",precision=22)
    @Basic
    private Double unitprice;


    @Column(name="partnumber",table="transaction",nullable=false,length=50)
    @Basic
    private String partnumber;


    @Column(name="sellingprice",table="transaction",precision=22)
    @Basic
    private Double sellingprice;


    @Column(name="totalprice",table="transaction",precision=22)
    @Basic
    private Double totalprice;


    @Column(name="manufacturerflag",table="transaction",nullable=false)
    @Basic
    private int manufacturerflag;


    @ManyToOne(optional=false,targetEntity=User.class)
    @JoinColumn(name="userid",referencedColumnName="userid",insertable=true,nullable=true,unique=false,updatable=true)
    private User userid;


    @Column(name="quantity",table="transaction",nullable=false)
    @Basic
    private int quantity;


    @Column(name="transactionnumber",table="transaction",nullable=false)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer transactionnumber;

    public Transaction(){

    }


   public String getManufacturerconsumerid() {
        return this.manufacturerconsumerid;
    }


  public void setManufacturerconsumerid (String manufacturerconsumerid) {
        this.manufacturerconsumerid = manufacturerconsumerid;
    }



   public int getInoutflag() {
        return this.inoutflag;
    }


  public void setInoutflag (int inoutflag) {
        this.inoutflag = inoutflag;
    }



   public Date getTransactiondate() {
        return this.transactiondate;
    }


  public void setTransactiondate (Date transactiondate) {
        this.transactiondate = transactiondate;
    }



   public int getDamageditemflag() {
        return this.damageditemflag;
    }


  public void setDamageditemflag (int damageditemflag) {
        this.damageditemflag = damageditemflag;
    }



   public Double getUnitprice() {
        return this.unitprice;
    }


  public void setUnitprice (Double unitprice) {
        this.unitprice = unitprice;
    }



   public String getPartnumber() {
        return this.partnumber;
    }


  public void setPartnumber (String partnumber) {
        this.partnumber = partnumber;
    }



   public Double getSellingprice() {
        return this.sellingprice;
    }


  public void setSellingprice (Double sellingprice) {
        this.sellingprice = sellingprice;
    }



   public Double getTotalprice() {
        return this.totalprice;
    }


  public void setTotalprice (Double totalprice) {
        this.totalprice = totalprice;
    }



   public int getManufacturerflag() {
        return this.manufacturerflag;
    }


  public void setManufacturerflag (int manufacturerflag) {
        this.manufacturerflag = manufacturerflag;
    }



   public User getUserid() {
        return this.userid;
    }


  public void setUserid (User userid) {
        this.userid = userid;
    }



   public int getQuantity() {
        return this.quantity;
    }


  public void setQuantity (int quantity) {
        this.quantity = quantity;
    }



   public Integer getTransactionnumber() {
        return this.transactionnumber;
    }


  public void setTransactionnumber (Integer transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

}

