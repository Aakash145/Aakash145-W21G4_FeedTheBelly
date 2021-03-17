package com.example.foodrescue;

import java.io.Serializable;

public class Dishes implements Serializable {

    private String dishName;
    private int noOfPlates;
    private double weight;


    public Dishes(String dishName, int noOfPlates, double weight) {
        this.dishName = dishName;
        this.noOfPlates = noOfPlates;
        this.weight = weight;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getNoOfPlates() {
        return noOfPlates;
    }

    public void setNoOfPlates(int noOfPlates) {
        this.noOfPlates = noOfPlates;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
