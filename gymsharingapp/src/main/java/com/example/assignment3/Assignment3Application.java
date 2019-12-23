package com.example.assignment3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.assignment3.Entities.*;
import com.example.assignment3.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;

@SpringBootApplication
public class Assignment3Application implements CommandLineRunner{

	@Autowired
	GymRepository gymRepository;


	@Autowired
	PersonalTrainerRepository ptRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	SubscriptionRepository subRepository;

	public static void main(String[] args) {
		SpringApplication.run(Assignment3Application.class, args);
	}

		@Override
		public void run(String... args) throws Exception {

			//create Gym
			Gym gym = new Gym("MaxiFit",  "Via le mani dal naso", "22", "Vimodrone", "maxifit@gmail.com", "3333333333");

			//create User
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			Date d1 = df.parse("18-04-1996");
			User user = new User("Andrea", "Carubelli", d1, "23", "CRBNDR96D18I577C", "Via fratelli Cairoli", "24", "Vimodrone", "a.carubelli@campus.unimib.it", "3333333333");
			User user2 = new User("Pippo", "Buado", d1, "23", "CRBNDR96D18I577C", "Via fratelli Cairoli", "24", "Vimodrone", "a.carubelli@campus.unimib.it", "3333333333");

			//create Personal trainer
			DateFormat df1 = new SimpleDateFormat("MM-dd-yyyy");
			Date d2 = df1.parse("28-12-1995");
			PersonalTrainer pt = new PersonalTrainer("Gianmaria", "Balducci", d2, "23", "BLDGMR95T28F205N", "Fit gimmy", "Esperto", "g.balducci1@campus.unimib.it", "123456789");

			//create subscriptions
			Subscription annualSub = new AnnualSubscription(gym.getId(), "300");
			Subscription monthSub = new MonthSubscription(gym.getId(), "30");
			Subscription trialSub = new TrialSubscription(gym.getId(), "0");

			//Save objects
			ptRepository.save(pt);			
			gymRepository.save(gym);
			userRepository.save(user);
			userRepository.save(user2);
			subRepository.save(annualSub);
			subRepository.save(monthSub);
			subRepository.save(trialSub);
			
			//Set relationship
			user.setPersonalTrainer(pt);
			pt.getUsers().add(user);
			pt.getUsers().add(user2);

			gym.getPersonalTrainers().add(pt);
			pt.getGyms().add(gym);

			gym.getSubscriptions().add(annualSub);
			gym.getSubscriptions().add(monthSub);
			gym.getSubscriptions().add(trialSub);

			//Update objects

			//userRepository.save(user);
			gymRepository.save(gym);
			//ptRepository.save(pt);	

		}

}
