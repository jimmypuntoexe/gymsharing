package com.example.assignment3.Controller;

import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.PersonalTrainer;
import com.example.assignment3.Entities.Subscription;
import com.example.assignment3.Entities.User;
import com.example.assignment3.Repository.GymRepository;
import com.example.assignment3.Repository.PersonalTrainerRepository;
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
public class GymController {

  @Autowired
  GymRepository Gymrepository;

  @Autowired
  PersonalTrainerRepository PTRepository;

  @Autowired
  SubscriptionRepository subRepository;

  @Autowired
  UserRepository userRepository;

  @RequestMapping("/insertGym")
  public String insertGym(Model model) {
    model.addAttribute("action", "insert");
    return "insertGym";
  }

  @RequestMapping(value="/deleteGym/{id}", method=RequestMethod.GET)
	public String gymDelete(@PathVariable Long id) {
      Gymrepository.delete(id);
      
      return "redirect:/gyms/";
  }

  @RequestMapping(value="/modifyGym/{id}", method=RequestMethod.GET)
  public String updateUser( @PathVariable Long id, Model model) {
          Gym gym = Gymrepository.findOne(id);
          model.addAttribute("gym", gym);
          model.addAttribute("action", "edit");
          return "insertGym";
  }

  @RequestMapping(value="/{idGym}/{idPtr}/deletePersonalTrainer", method=RequestMethod.GET)
	public String deletePersonalTrainer(@PathVariable Long idGym, @PathVariable Long idPtr) {
      Gym gym = Gymrepository.findOne(idGym);
      PersonalTrainer ptr = PTRepository.findOne(idPtr);
      gym.getPersonalTrainers().remove(ptr);
      Gymrepository.save(gym);
      return "redirect:/gym/" + gym.getId();
  }

  @RequestMapping(value="/updateGym/{id}", method=RequestMethod.GET)
	public String GymUpdate(@PathVariable Long id,
            @RequestParam String name,
            @RequestParam String address, @RequestParam String civicNumber,
            @RequestParam String city, @RequestParam String email, @RequestParam String phoneNumber, 
            Model model) {
        Gym gym = Gymrepository.findOne(id);
        gym.setName(name);
        gym.setAddress(address);
        gym.setCivicNumber(civicNumber);
        gym.setCity(city);
        gym.setEmail(email);
        gym.setPhoneNumber(phoneNumber);
        Gymrepository.save(gym);

        //da sistemare qui sotto
        model.addAttribute("gym", gym);
        return "redirect:/gyms/";
  }

  
  @RequestMapping("/gym/{id}")
	public String gym(@PathVariable Long id, Model model) {
        model.addAttribute("gym", Gymrepository.findOne(id));
        model.addAttribute("sub", subRepository.findAll());
        return "infoGym";
	}

  @RequestMapping(value="/gyms", method=RequestMethod.GET)
	public String gymList(Model model) {
        model.addAttribute("gyms", Gymrepository.findAll());
        return "gyms";
  }
  
  @RequestMapping(value="/gymsForSubscriber/{id}", method=RequestMethod.GET)
	public String gymList(@PathVariable Long id, Model model) {
        model.addAttribute("personalTrainer", PTRepository.findOne(id));
        model.addAttribute("gyms", Gymrepository.findAll());
        return "gymsForSubscriber";
  }

  
  @RequestMapping(value="/gymsForSubscription/{id}", method=RequestMethod.GET)
	public String gymListForSubscription(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));
        model.addAttribute("gyms", Gymrepository.findAll());
        return "gymsForSubscription";
  }
  @RequestMapping(value="/gym/{idGym}/{idSub}/{idUser}/buySubscription")
    public String buySubscription(@PathVariable Long idGym, @PathVariable Long idSub, 
    @PathVariable Long idUser, Model model){
        model.addAttribute("gym", Gymrepository.findOne(idGym));
        model.addAttribute("user", userRepository.findOne(idUser));
        model.addAttribute("sub", subRepository.findOne(idSub));
        User user = userRepository.findOne(idUser);
        Subscription sub = subRepository.findOne(idSub);
        user.setSubscription(sub);
        return "infoUser";
    }

    @RequestMapping(value="/searchGym", method=RequestMethod.GET)
    public String gymSearch(@RequestParam String name, Model model) {
          model.addAttribute("gyms", Gymrepository.findByName(name));
          return "gyms";
    }
  
  @RequestMapping(value="/infoGymForSubscription/{idUser}/{idGym}", method=RequestMethod.GET)
	public String gymListForSubscription(@PathVariable Long idUser, @PathVariable Long idGym, Model model) {
        model.addAttribute("user", userRepository.findOne(idUser));
        model.addAttribute("gym", Gymrepository.findOne(idGym));
        return "infoGymForSubscription";
        //return "redirect:/infoGymForSubscription/" + idGym + "/" + idUser;
  }

  @RequestMapping(value="/insertGym", method=RequestMethod.POST)
	public String GymAdd(
            @RequestParam String name, @RequestParam String address, @RequestParam String civicNumber,
            @RequestParam String city, @RequestParam String email, @RequestParam String phoneNumber,
            Model model) {
        Gym newGym = new Gym();
        newGym.setName(name);
        newGym.setAddress(address);
        newGym.setCivicNumber(civicNumber);
        newGym.setCity(city);
        newGym.setEmail(email);
        newGym.setPhoneNumber(phoneNumber);
        Gymrepository.save(newGym);

        //da sistemare qui sotto
        model.addAttribute("gyms", newGym);
        return "redirect:/gyms/";
  }

  @RequestMapping(value="/gym/{id}/insertAffiliate", method=RequestMethod.GET)
  public String affiliate(@PathVariable Long id, Model model){
    model.addAttribute("gyms", Gymrepository.findAll());
    model.addAttribute("gym2", Gymrepository.findOne(id));
    return "affiliateGyms";
  }

  @RequestMapping(value="/addAffiliate/{idGym}/{idGymAffiliate}", method=RequestMethod.GET)
  public String addAffiliate(@PathVariable Long idGym, @PathVariable Long idGymAffiliate, Model model){
    Gym gym1 = Gymrepository.findOne(idGym);
    Gym gym2 = Gymrepository.findOne(idGymAffiliate);
    gym2.getAffiliateGyms().add(gym1);
    Gymrepository.save(gym1);
    return "redirect:/gyms/";
  }

}