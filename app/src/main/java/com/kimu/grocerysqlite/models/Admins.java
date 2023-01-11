package com.kimu.grocerysqlite.models;

/**
 * CREATED BY HAMZA-ALI 12-09-2022
 */
public class Admins {
    private String id, name, email, pass;

    public Admins(String id, String name, String email, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setPass(String pass) {this.pass = pass;}

    public String getId() {return this.id;}
    public String getName() {return this.name;}
    public String getEmail() {return this.email;}
    public String getPass() {return this.pass;}
}
