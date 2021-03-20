package com.example.foodrescue;

import java.io.Serializable;

public class Detail implements Serializable {
    private String ID, Add1, City, State, Country, Postal, id;

    public Detail(String id, String ID, String add1, String city, String state, String country, String postal) {
        this.ID = ID;
        this.Add1 = add1;
        this.City = city;
        this.State = state;
        this.Country = country;
        this.Postal = postal;
        this.id = id;
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

    public String getPostal() {
        return Postal;
    }


}
