package com.example.dbdemo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class MyContact {

    @Id
    private int id;

    @Column(nullable = false, unique = true, length = 12)
    private String mobile;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(length = 45)
    private String address;

    @Column(length = 20)
    private String QQ;

    @Column(length = 45)
    private String email;


    public MyContact(String name, String email, String mobile) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = "NULL";
        this.QQ = "NULL";
    }

    public MyContact() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQQ() {
        return this.QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
}
