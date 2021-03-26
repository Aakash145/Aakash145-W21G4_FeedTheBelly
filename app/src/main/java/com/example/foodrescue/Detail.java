package com.example.foodrescue;

import java.io.Serializable;

public class Detail implements Serializable {
    private String ID, Add1, City, State, Country, Postal, Email, Phone, Name;

    public Detail(String email, String name, String Phone, String ID, String add1, String city, String state, String country, String postal) {
        this.Email = email;
        this.Name = name;
        this.Phone = Phone;
        this.ID = ID;
        this.Add1 = add1;
        this.City = city;
        this.State = state;
        this.Country = country;
        this.Postal = postal;

    }

    public Detail() {

    }

    public String getID() {
        return ID;
    }

    public String getAdd1() {
        return Add1;
    }


    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return Country;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setAdd1(String add1) {
        Add1 = add1;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setState(String state) {
        State = state;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setPostal(String postal) {
        Postal = postal;
    }

    public String getPostal() {
        return Postal;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
