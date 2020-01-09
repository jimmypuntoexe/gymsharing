package com.example.assignment3.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private long idGym;

    @OneToMany
    private List<User> users;

    @OneToOne
    private Gym gym;

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

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Gym getGym() {
        return this.gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}