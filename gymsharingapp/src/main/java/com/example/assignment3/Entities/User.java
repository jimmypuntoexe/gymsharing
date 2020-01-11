package com.example.assignment3.Entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String birthDate;
    private String age;
    private String CF;
    private String address;
    private String civicNumber;
    private String city;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    @OneToOne
    private Subscription subscription;

    @OneToOne
    private PersonalTrainer personalTrainer;


	public User() {
		super();
	}

    public User(String name, String surname, String birthDate, String age, String CF, 
            String address, String civicNumber, String city, String email, String phoneNumber, String username,
            String password) {
		super();
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.age = age;
        this.CF = CF;
		this.address = address;
        this.civicNumber = civicNumber;
        this.city = city;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;

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

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCF() {
        return this.CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
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


    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public Subscription getSubscriptions() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
    }

	public PersonalTrainer getPersonalTrainer() {
		return personalTrainer;
    }
    
    public void setPersonalTrainer(PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}