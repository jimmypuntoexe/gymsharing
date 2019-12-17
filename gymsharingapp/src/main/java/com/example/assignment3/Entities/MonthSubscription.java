package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class MonthSubscription extends Subscription{
    private static final int lifeSubscription = 30;
    private String price;

    public MonthSubscription(long id, String monthPrice) {
        super(id);
        this.price = monthPrice;
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