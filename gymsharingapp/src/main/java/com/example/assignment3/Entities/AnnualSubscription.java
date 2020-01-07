package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class AnnualSubscription extends Subscription{
    private static final String lifeSubscription = "365";
    private static final String type = "Annual";
    private String price;

    public AnnualSubscription(){
        super();
    }

    public AnnualSubscription(long id, String annualPrice) {
        super(id);
        this.price = annualPrice;
    }

    public static String getLifeSubscription() {
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