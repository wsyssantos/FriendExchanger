package com.list.contact.example.contactlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wesley on 9/1/16.
 */
public class Transfer {
    @SerializedName("Id")
    private Integer id;
    @SerializedName("ClienteId")
    private String clientId;
    @SerializedName("Valor")
    private Double value;
    @SerializedName("Token")
    private String token;
    @SerializedName("Data")
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Contact getContact(List<Contact> contactList) {
        Contact selectedContact = null;

        for(Contact contact : contactList) {
            int _contId = Integer.valueOf(contact.getId());
            int _clientId = Integer.valueOf(clientId);

            if(_contId == _clientId) {
                selectedContact = contact;
                break;
            }
        }

        return selectedContact;
    }
}
