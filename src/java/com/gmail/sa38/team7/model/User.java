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
@Table(name="user")
public  class User implements Serializable {


    @Column(name="userpassword",table="user",nullable=false,length=50)
    @Basic
    private String userpassword;


    @Column(name="username",table="user",nullable=false,length=50)
    @Basic
    private String username;


    @Column(name="userid",table="user",nullable=false,length=50)
    @Id
    private String userid;


    @Column(name="userrole",table="user",nullable=false,length=50)
    @Basic
    private String userrole;


    @OneToMany(targetEntity=Transaction.class,mappedBy="userid")
    private Collection<Transaction> transactionCollection;

    public User(){

    }


   public String getUserpassword() {
        return this.userpassword;
    }


  public void setUserpassword (String userpassword) {
        this.userpassword = userpassword;
    }



   public String getUsername() {
        return this.username;
    }


  public void setUsername (String username) {
        this.username = username;
    }



   public String getUserid() {
        return this.userid;
    }


  public void setUserid (String userid) {
        this.userid = userid;
    }



   public String getUserrole() {
        return this.userrole;
    }


  public void setUserrole (String userrole) {
        this.userrole = userrole;
    }



   public Collection<Transaction> getTransactionCollection() {
        return this.transactionCollection;
    }


  public void setTransactionCollection (Collection<Transaction> transactionCollection) {
        this.transactionCollection = transactionCollection;
    }

}

