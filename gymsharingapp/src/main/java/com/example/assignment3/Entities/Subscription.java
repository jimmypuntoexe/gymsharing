package com.example.assignment3.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long idGym;

	public Subscription() {
		super();
    }

	public Subscription(long idGym) {
        super();
        this.idGym = idGym;
    }

    public long getId() {
        return this.id;
    }

    public long getIdGym() {
        return this.idGym;
    }
}