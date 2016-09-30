package com.list.contact.example.contactlist.model;

/**
 * Created by wesley on 9/1/16.
 */
public class Contact {
    private String id;
    private String name;
    private String phone;
    private Integer photoUrl;

    public Contact(String id, String name, String phone, Integer photoUrl) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.photoUrl = photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Integer photoUrl) {
        this.photoUrl = photoUrl;
    }
}
