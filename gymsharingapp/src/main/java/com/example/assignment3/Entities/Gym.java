package com.example.assignment3.Entities;

import java.util.List;
import java.util.Set;

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
public class Gym {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String address;
	private String civicNumber;
	private String city;
	private String email;
	private String phoneNumber;

	// @ManyToOne
	// //da capire cos'è referencedColumnName
	// @JoinColumn(referencedColumnName = "id")
	// @JsonBackReference
	// private List<Gym> affiliateGym;

	@ManyToMany
	private Set<PersonalTrainer> personalTrainer;

	@OneToMany
	private Set<Subscription> subscription;

	public Gym() {
		super();
		
	}

	public Gym(String name, String address, String civicNumber, String city,
			String email, String phoneNumber, List<Gym> affiliateGym) {
		super();
		this.name = name;
		this.address = address;
		this.civicNumber = civicNumber;
		this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

	// public List<Gym> getAffialiateGym() {
	// 	return affiliateGym;
	// }

	// public void setAffialiteGym(List<Gym> affiliateGym) {
	// 	this.affiliateGym = affiliateGym;
	// }

}