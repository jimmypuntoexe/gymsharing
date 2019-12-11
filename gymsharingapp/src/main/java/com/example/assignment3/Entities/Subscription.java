package com.example.assignment3.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;


	public Subscription(long id) {
		super();
    }
}