package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class MonthSubscription extends Subscription{
    private static final int lifeSubscription = 30;

    public MonthSubscription(long id) {
        super(id);
    }
}