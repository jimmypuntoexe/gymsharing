package com.example.assignment3.Controller;

import javax.websocket.server.PathParam;

import com.example.assignment3.Entities.AnnualSubscription;
import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.Subscription;
import com.example.assignment3.Repository.GymRepository;
import com.example.assignment3.Repository.SubscriptionRepository;
import com.example.assignment3.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscriptionController {

    @Autowired
    SubscriptionRepository subRepository;

    @Autowired
    GymRepository gymRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/gym/{id}/insertSubscriptions")
    public String subscriptionsGym(@PathVariable Long id, Model model) {
        model.addAttribute("gym", gymRepository.findOne(id));
      return "insertSubscriptions";
    }

    @RequestMapping(value="/gym/{id}/insertSubscriptions", method=RequestMethod.POST)
	public String gymAddSubscription(@PathVariable Long id, Model model, String annualPrice) {
        Gym gym = gymRepository.findOne(id);
        if(annualPrice != null){
            Subscription newAnnualSubscription = new AnnualSubscription(id, annualPrice);
            gym.getSubscriptions().add(newAnnualSubscription);
            subRepository.save(newAnnualSubscription);
            System.out.println(((AnnualSubscription)(gym.getSubscriptions().get(0))).getPrice());
        }  
        model.addAttribute("gym", subRepository.findAll());
    	// if (gym != null) {
    	// 	if (!gym.hasSkill(skill)) {
    	// 		developer.getSkills().add(skill);
    	// 	}
    	// 	repository.save(developer);
        //     model.addAttribute("developer", repository.findOne(id));
        //     model.addAttribute("skills", skillRepository.findAll());
        //     return "redirect:/developer/" + developer.getId();
    	// }

        // model.addAttribute("developers", repository.findAll());
        return "redirect:/gym/" + gym.getId();
    }

}