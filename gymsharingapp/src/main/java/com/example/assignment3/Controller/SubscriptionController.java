package com.example.assignment3.Controller;

import com.example.assignment3.Entities.AnnualSubscription;
import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.MonthSubscription;
import com.example.assignment3.Entities.Subscription;
import com.example.assignment3.Entities.TrialSubscription;
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
        model.addAttribute("action", "insert");
        model.addAttribute("sub", subRepository.findOne(id));
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
        
        //model.addAttribute("subscriptions", subRepository.findAll());
        //model.addAttribute("gym", subRepository.findAll());

        return "redirect:/gym/" + gym.getId();
    }

    @RequestMapping(value="/gym/{idGym}/{idSub}/deleteSubscription", method=RequestMethod.GET)
	public String subDelete(@PathVariable Long idGym, @PathVariable Long idSub) {
        Gym gym = gymRepository.findOne(idGym);
        Subscription sub = subRepository.findOne(idSub);
        gym.getSubscriptions().remove(sub);
        subRepository.delete(sub.getId());
        return "redirect:/gym/" + gym.getId();
    }

    @RequestMapping("/gym/{idGym}/{idSub}/editSubscriptions")
    public String editSubscriptionsGym(@PathVariable Long idGym, @PathVariable Long idSub,
     Model model) {
        Gym gym = gymRepository.findOne(idGym);
        Subscription sub = subRepository.findOne(idSub);
        model.addAttribute("action", "edit");
        model.addAttribute("classType", subRepository.findOne(idSub).getClass().getSimpleName());
        model.addAttribute("sub", sub); 
        model.addAttribute("gym", gym);
        //return "redirect:/gym/" + gym.getId()+ "/" + sub.getId() +"/insertSubscriptions";
        return "insertSubscriptions";
    }

    @RequestMapping(value="/gym/{idGym}/{idSub}/updateSubscriptions", method=RequestMethod.GET)
    public String updateSubscription(@PathVariable Long idGym, @PathVariable Long idSub, Model model, 
    @RequestParam(required = false) String annualPrice,
    @RequestParam(required = false) String monthPrice,
    @RequestParam(required = false) String trialPrice) {
        Gym gym = gymRepository.findOne(idGym);
        String classType = subRepository.findOne(idSub).getClass().getSimpleName();
        switch(classType){
            case "AnnualSubscription":
            Subscription annualSubscription = subRepository.findOne(idSub);
            ((AnnualSubscription) annualSubscription).setPrice(annualPrice);
            subRepository.save(annualSubscription);
            break;
            case "MonthSubscription":
            Subscription monthSubscription = subRepository.findOne(idSub);
            ((MonthSubscription) monthSubscription).setPrice(monthPrice);
            subRepository.save(monthSubscription);
            break;
            case "TrialSubscription":
            Subscription trialSubscription = subRepository.findOne(idSub);
            ((TrialSubscription) trialSubscription).setPrice(trialPrice);
            subRepository.save(trialSubscription);
            break;
        }
        model.addAttribute("gym", gym);
        model.addAttribute("sub", subRepository.findOne(idSub));

        return "redirect:/gym/" + gym.getId();
    }

/*
    @RequestMapping(value="/{idUsr}/{idSub}/{idGym}/deleteSubScription", method=RequestMethod.GET)
    public String deletePersonalTrainer(@PathVariable Long idUsr, @PathVariable Long idSub,
    @PathVariable Long idGym) {
        Gym gym = gymRepository.findOne(idGym);
        User user = userRepository.findOne(idUsr);
        Subscription sub = subRepository.findOne(idSub);
        user.setSubscription(null);
        //sub.setPrice(null);

        userRepository.save(user);
        return "redirect:/gym/" + user.getId() + "/" + gym.getId() + "/" + sub.getId() + "/buySubscription" ;
  }
  */

}