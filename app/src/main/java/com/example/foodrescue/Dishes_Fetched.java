package com.example.foodrescue;

public class Dishes_Fetched {
    String id;
    String name;
    String cuisine;
    String category;
    String weight;
    String plates;

    public Dishes_Fetched(String id, String name, String cuisine, String category, String plates, String weight) {
        this.id = id;
        this.name = name;
        this.cuisine = cuisine;
        this.category = category;
        this.weight = weight;
        this.plates = plates;
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
    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}
