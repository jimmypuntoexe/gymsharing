package com.example.assignment3.Entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PersonalTrainer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private Date birthDate;
    private String age;
    private String patent;
    private String level;
    private String CF;
    private String email;
    private String phoneNumber;

    @OneToMany
    private Set<User> user;

    @ManyToMany(targetEntity = Gym.class)
    private Set<Gym> gym;


	public PersonalTrainer() {
		super();
	}

    public PersonalTrainer(String name, String surname, Date birthDate, String age, String CF, 
            String patent, String level, String email, String phoneNumber) {
		super();
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.age = age;
        this.CF = CF;
		this.patent = patent;
        this.level = level;
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

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPatent() {
        return this.patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCF() {
        return this.CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
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

}