package com.example.assignment3;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
    private String address;
    private String civicNumber;
    private String email;
    private String phoneNumber;
	@ManyToMany
	private List<Gym> affiliateGym;

	public Gym() {
		super();
	}

	public Gym(String name, String address, String civicNumber,
			String email, String phoneNumber, List<Gym> affiliateGym) {
		super();
		this.name = name;
		this.address = address;
		this.civicNumber = civicNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Gym> getAffialiateGym() {
		return affiliateGym;
	}

	public void setAffialiteGym(List<Gym> affiliateGym) {
		this.affiliateGym = affiliateGym;
	}

	// public boolean hasSkill(Skill skill) {
	// 	for (Skill containedSkill: getSkills()) {
	// 		if (containedSkill.getId() == skill.getId()) {
	// 			return true;
	// 		}
	// 	}
	// 	return false;
	// }

}