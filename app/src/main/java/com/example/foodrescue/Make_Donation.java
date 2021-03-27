package com.example.foodrescue;

public class Make_Donation {
    int donationID;
    String id;
    String name;
    String cuisine;
    String category;
    String weight;
    String plates;

    public Make_Donation(int donationID, String id, String name, String cuisine, String category, String weight, String plates) {
        this.donationID = donationID;
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.category = category;
        this.weight = weight;
        this.plates = plates;
    }

    public int getDonationID() {
        return donationID;
    }

    public void setDonationID(int donationID) {
        this.donationID = donationID;
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }
}
