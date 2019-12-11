package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class AnnualSubscription extends Subscription{
    private static final int lifeSubscription = 365;

    public AnnualSubscription(long id) {
        super(id);
    }
}