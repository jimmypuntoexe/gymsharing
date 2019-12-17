package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class TrialSubscription extends Subscription{
    private static final int lifeSubscription = 1;
    private String price;

    public TrialSubscription(long id, String trialPrice) {
        super(id);
        this.price = trialPrice;
    }

    /**
     * @return the lifesubscription
     */
    public static int getLifesubscription() {
        return lifeSubscription;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}