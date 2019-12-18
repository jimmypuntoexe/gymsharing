package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class MonthSubscription extends Subscription{
    private static final int lifeSubscription = 30;
    private static final String type = "Month";
    private String price;

    public MonthSubscription(){
        super();
    }

    public MonthSubscription(long id, String monthPrice) {
        super(id);
        this.price = monthPrice;
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