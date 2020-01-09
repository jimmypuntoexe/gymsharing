package com.example.assignment3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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

			//create Gyms
			Gym gym = new Gym("MaxiFit",  "Via fratelli cairoli", "22", "Vimodrone", "maxifit@gmail.com", "3333333333", "maxipsw");
			Gym gym2 = new Gym("Virgin Active",  "Via santa marcellina", "22", "Vimodrone", "virgin_active@gmail.com", "3333333333", "virginpsw");


			//create Users
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			Date d1 = df.parse("18-04-1996");

			User user = new User("Andrea", "Carubelli", d1, "23", "CRBNDR96D18I577C", "Via fratelli Cairoli", "24", "Vimodrone", "a.carubelli@campus.unimib.it", "3333333333", "andrea", "andreapsw");
			User user2 = new User("Pippo", "Buado", d1, "23", "CRBNDR96D18I577C", "Via fratelli Cairoli", "24", "Vimodrone", "a.carubelli@campus.unimib.it", "3333333333", "pippo", "pippopsw");
			User user3 = new User("Giovanni", "EGiacomo", d1, "23", "CRBNDR96D18I577C", "Via fratelli Cairoli", "24", "Vimodrone", "a.carubelli@campus.unimib.it", "3333333333", "giovanni", "giovannipsw");

			//create Personal trainer
			DateFormat df1 = new SimpleDateFormat("MM-dd-yyyy");
			Date d2 = df1.parse("28-12-1995");

			PersonalTrainer pt = new PersonalTrainer("Gianmaria", "Balducci", d2, "23", "BLDGMR95T28F205N", "Fit gimmy", "Esperto", "g.balducci1@campus.unimib.it", "123456789", "gimmy", "gimmypsw");
			PersonalTrainer pt2 = new PersonalTrainer("Gianni", "Morandi", d2, "23", "BLDGMR95T28F205N", "Fit gianni", "Magico", "g.balducci1@campus.unimib.it", "123456789", "gianni", "giannipsw");

			//create subscriptions
			Subscription annualSub = new AnnualSubscription(gym.getId(), "300");
			Subscription monthSub = new MonthSubscription(gym.getId(), "30");
			Subscription trialSub = new TrialSubscription(gym.getId(), "0");

			//Save objects
			ptRepository.save(pt);
			ptRepository.save(pt2);				
			gymRepository.save(gym);
			gymRepository.save(gym2);
			userRepository.save(user);
			userRepository.save(user2);
			userRepository.save(user3);
			subRepository.save(annualSub);
			subRepository.save(monthSub);
			subRepository.save(trialSub);

			Gym g1 = gymRepository.findById(gym.getId());
			Gym g2 = gymRepository.findById(gym2.getId());

			User u1 = userRepository.findById(user.getId());
			User u2 = userRepository.findById(user2.getId());
			User u3 = userRepository.findById(user3.getId());

			PersonalTrainer p1 = ptRepository.findById(pt.getId());
			PersonalTrainer p2 = ptRepository.findById(pt2.getId());

			g1.setPersonalTrainers(Arrays.asList(p1, p2));
			g2.setPersonalTrainers(Arrays.asList(p1));

			g1.setSubscription(Arrays.asList(annualSub, monthSub, trialSub));

			g1.setAffiliateGyms(Arrays.asList(g2));

			p1.setGyms(Arrays.asList(g1, g2));
			p2.setGyms(Arrays.asList(g1));

			p1.setUsers(Arrays.asList(u1,u2));
			p2.setUsers(Arrays.asList(u3));


			u1.setPersonalTrainer(p1);
			u2.setPersonalTrainer(p1);
			u3.setPersonalTrainer(p2);

			u1.setSubscription(annualSub);
			u2.setSubscription(monthSub);

			annualSub.setUsers(Arrays.asList(u1));
			monthSub.setUsers(Arrays.asList(u2));

			//save object
			userRepository.save(u1);
			userRepository.save(u2);
			userRepository.save(u3);

			gymRepository.save(g1);
			gymRepository.save(g2);

			ptRepository.save(p1);
			ptRepository.save(p2);

			subRepository.save(annualSub);
			subRepository.save(monthSub);

		}

}
