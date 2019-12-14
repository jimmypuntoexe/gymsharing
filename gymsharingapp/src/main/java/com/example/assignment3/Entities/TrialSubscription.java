package com.example.assignment3.Entities;


import javax.persistence.Entity;

@Entity
public class TrialSubscription extends Subscription{
    private static final int lifeSubscription = 1;

    public TrialSubscription(long id) {
        super(id);
    }
}