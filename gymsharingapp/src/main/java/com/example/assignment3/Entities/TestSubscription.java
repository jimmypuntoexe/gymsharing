package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class TestSubscription extends Subscription{
    private static final int lifeSubscription = 1;

    public TestSubscription(long id) {
        super(id);
    }
}