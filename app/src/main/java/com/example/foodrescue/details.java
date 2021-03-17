package com.example.foodrescue;

public class details {
    String Rest_ID;
    String Add1;
    String Add2;
    String City;
    String State;
    String Country;
    String PostalCode;

    public details() {
    }

    public details(String rest_ID, String add1, String add2, String city, String state, String country, String postalCode) {
        Rest_ID = rest_ID;
        Add1 = add1;
        Add2 = add2;
        City = city;
        State = state;
        Country = country;
        PostalCode = postalCode;
    }

    public String getRest_ID() {
        return Rest_ID;
    }

    public void setRest_ID(String rest_ID) {
        Rest_ID = rest_ID;
    }

    public String getAdd1() {
        return Add1;
    }

    public void setAdd1(String add1) {
        Add1 = add1;
    }

    public String getAdd2() {
        return Add2;
    }

    public void setAdd2(String add2) {
        Add2 = add2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }
}
