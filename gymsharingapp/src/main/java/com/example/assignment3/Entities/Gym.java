package com.example.assignment3.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String address;
	private String civicNumber;
	private String city;
	private String email;
	private String phoneNumber;
	private String password;

	@ManyToMany
	private List<Gym> affiliateGyms;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<PersonalTrainer> personalTrainers;

	@OneToMany
	private List<Subscription> subscriptions;

	public Gym() {
		super();
		
	}

	public Gym(String name, String address, String civicNumber, String city,
			String email, String phoneNumber, String password) {
		super();
		this.name = name;
		this.address = address;
		this.civicNumber = civicNumber;
		this.city = city;
        this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.personalTrainers = new ArrayList<PersonalTrainer>();
		this.subscriptions = new ArrayList<Subscription>();
	}


	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCivicNumber() {
		return this.civicNumber;
	}

	public void setCivicNumber(String civicNumber) {
		this.civicNumber = civicNumber;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public List<PersonalTrainer> getPersonalTrainers() {
		return personalTrainers;
	}

	public void setSubscription(List<Subscription> subscription) {
		this.subscriptions = subscription;
	}

	public void setPersonalTrainers(List<PersonalTrainer> pt) {
		this.personalTrainers = pt;
	}

	public List<Gym> getAffiliateGyms() {
		return affiliateGyms;
	}

	public void setAffiliateGyms(List<Gym> affiliateGym) {
		this.affiliateGyms = affiliateGym;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}