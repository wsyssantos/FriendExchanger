package com.list.contact.example.contactlist.model;

/**
 * Created by wesley on 9/1/16.
 */
public class User {
    private String name;
    private String email;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
