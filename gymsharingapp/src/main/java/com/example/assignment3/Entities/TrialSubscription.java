package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class TrialSubscription extends Subscription{
    private static final int lifeSubscription = 1;
    private static final String type = "Trial";
    private String price;

    public TrialSubscription(){
        super();
    }

    public TrialSubscription(long id, String trialPrice) {
        super(id);
        this.price = trialPrice;
    }

    public static int getLifeSubscription() {
        return lifeSubscription;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static String getType() {
        return type;
    }
}