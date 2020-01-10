package com.example.assignment3.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class PersonalTrainer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long idGym;
    private String name;
    private String surname;
    private Date birthDate;
    private String age;
    private String patent;
    private String level;
    private String CF;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    @OneToMany
    private List<User> users;

    @ManyToMany
    private List<Gym> gyms;

	public PersonalTrainer() {
		super();
	}

    public PersonalTrainer(String name, String surname, Date birthDate, String age, String CF, 
            String patent, String level, String email, String phoneNumber, String username, String password) {
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
        this.username = username;
        this.password = password;
        this.gyms = new ArrayList<Gym>();
        this.users = new ArrayList<User>();

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

    public List<Gym> getGyms() {
		return gyms;
    }

    public void setGyms(List<Gym> gyms) {
        this.gyms = gyms;
    }
    
    public long getIdGym() {
        return this.idGym;
    }

    public void setIdGym(Long idGym) {
         this.idGym = idGym;
    }

	public List<User> getUsers() {
		return users;
    }
    
    public void setUsers(List<User> users) {
        this.users = users;
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