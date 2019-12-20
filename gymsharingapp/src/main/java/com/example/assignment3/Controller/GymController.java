package com.example.assignment3.Controller;

import com.example.assignment3.Entities.Gym;

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
  public String insertGym() {
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
          return "insertGym";
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

  @RequestMapping(value="/gymForSubscription/{idUser}/{idGym}", method=RequestMethod.GET)
	public String gymListForSubscription(@PathVariable Long idUser, @PathVariable Long idGym, Model model) {
        model.addAttribute("users", userRepository.findOne(idUser));
        model.addAttribute("gyms", Gymrepository.findOne(idGym));
        return "redirect:/infoGymForSubscription/" + idGym + "/" + idUser;
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

}