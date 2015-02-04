package com.gmail.sa38.team7.model;

import java.io.Serializable;

import java.lang.Integer;
import java.lang.String;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authentication_table")
public  class AuthenticationTable implements Serializable {


    @Column(name="userpassword",table="authentication_table",length=45)
    @Basic
    private String userpassword;


    @Column(name="username",table="authentication_table",length=45)
    @Basic
    private String username;


    @Column(name="userid",table="authentication_table",nullable=false)
    @Id
    private Integer userid;


    @Column(name="userrole",table="authentication_table",length=45)
    @Basic
    private String userrole;

    public AuthenticationTable(){

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



   public Integer getUserid() {
        return this.userid;
    }


  public void setUserid (Integer userid) {
        this.userid = userid;
    }



   public String getUserrole() {
        return this.userrole;
    }


  public void setUserrole (String userrole) {
        this.userrole = userrole;
    }

}

