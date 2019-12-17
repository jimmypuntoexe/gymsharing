package com.example.assignment3.Controller;

import javax.websocket.server.PathParam;

import com.example.assignment3.Entities.AnnualSubscription;
import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.MonthSubscription;
import com.example.assignment3.Entities.Subscription;
import com.example.assignment3.Entities.TrialSubscription;
import com.example.assignment3.Repository.GymRepository;
import com.example.assignment3.Repository.SubscriptionRepository;

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

    @RequestMapping("/gym/{id}/insertSubscriptions")
    public String subscriptionsGym(@PathVariable Long id, Model model) {
        model.addAttribute("gym", gymRepository.findOne(id));
      return "insertSubscriptions";
    }

    @RequestMapping(value="/gym/{id}/insertSubscriptions", method=RequestMethod.POST)
    public String gymAddSubscription(@PathVariable Long id, Model model, 
    @RequestParam(required = false) String annualPrice,
    @RequestParam(required = false) String monthPrice,
    @RequestParam(required = false) String trialPrice) {
        Gym gym = gymRepository.findOne(id);
        if(!(annualPrice.isEmpty())){
            Subscription newAnnualSubscription = new AnnualSubscription(id, annualPrice);
            gym.getSubscriptions().add(newAnnualSubscription);
            subRepository.save(newAnnualSubscription);
        }
        if(!(monthPrice.isEmpty())){
            Subscription newMonthSubscription = new MonthSubscription(id, monthPrice);
            gym.getSubscriptions().add(newMonthSubscription);
            subRepository.save(newMonthSubscription);
        }
        if(!(trialPrice.isEmpty())){
            Subscription newTrialSubscription = new TrialSubscription(id, trialPrice);
            gym.getSubscriptions().add(newTrialSubscription);
            subRepository.save(newTrialSubscription);
        }
        
        model.addAttribute("subscriptions", subRepository.findAll());
        model.addAttribute("gym", subRepository.findAll());

        return "redirect:/gym/" + gym.getId();
    }

}