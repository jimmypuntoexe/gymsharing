package com.example.assignment3.Controller;

import java.util.ArrayList;
import java.util.List;



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
      Gym gym = Gymrepository.findOne(id);
      List<PersonalTrainer> pts = gym.getPersonalTrainers();
      List<Subscription> subs = gym.getSubscriptions();
      // List<Gym> affiliateGyms = gym.getAffiliateGyms();

      if (!(pts.isEmpty())){
        for(PersonalTrainer pt:pts){
          pt.getGyms().remove(gym);
          PTRepository.save(pt);
        }
      }
      if (!(subs.isEmpty())){
        for(Subscription sub : subs){
          sub.setGym(null);
          subRepository.save(sub);
          }
        for(Subscription sub : subs){
          List<User> users = sub.getUsers();
          if (!(users.isEmpty())){
            for (User user : users){
              user.setSubscription(null);
              userRepository.save(user);
            }
            sub.setUsers(null);
          }
          subRepository.save(sub);
        }
      }
      // if (!(affiliateGyms.isEmpty())){
      //   for(Gym affiliate : affiliateGyms){
      //     affiliate.
      //     }
      // }

      Gymrepository.delete(id);
      
      return "redirect:/";
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
      ptr.getGyms().remove(gym);
      PTRepository.save(ptr);
      Gymrepository.save(gym);
      return "redirect:/gymAccount/{idGym}/myProfile";
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
        return "redirect:/gymAccount/{id}";
  }

  @RequestMapping("/gymAccount/{idGym}/myProfile")
	public String gymProfile(@PathVariable Long idGym, Model model) {
        model.addAttribute("action", "my");
        model.addAttribute("gym", Gymrepository.findOne(idGym));
        model.addAttribute("sub", subRepository.findAll());
        return "infoGym";
	}
  
  @RequestMapping("/gym/{id}")
	public String gym(@PathVariable Long id, Model model) {
        model.addAttribute("action", "affiliate");
        model.addAttribute("gym", Gymrepository.findOne(id));
        model.addAttribute("sub", subRepository.findAll());
        return "infoGym";
  }
  
  @RequestMapping("/gymAccount/{idGym}/gymAffiliate/{idGymAffiliate}")
	public String infoGymAffiliate(@PathVariable Long idGym, @PathVariable Long idGymAffiliate, Model model) {
        model.addAttribute("action", "infoAffiliate");
        model.addAttribute("gym", Gymrepository.findOne(idGym));
        model.addAttribute("gymAffiliate", Gymrepository.findOne(idGymAffiliate));
        model.addAttribute("sub", subRepository.findAll());
        return "infoGym";
	}

  @RequestMapping(value="/gyms", method=RequestMethod.GET)
	public String gymList(Model model) {
        model.addAttribute("gyms", Gymrepository.findAll());
        return "gyms";
  }
  
  @RequestMapping(value="/personalTrainerAccount/{idPt}/gymsForSubscriber", method=RequestMethod.GET)
	public String gymListForPt(@PathVariable Long idPt, Model model) {
        model.addAttribute("personalTrainer", PTRepository.findOne(idPt));
        model.addAttribute("gyms", Gymrepository.findAll());
        return "gymsForSubscriber";
  }

  @RequestMapping(value="/infoGymForPersonalTrainers/{idGym}", method=RequestMethod.GET)
	public String infoGymForPersonalTrainer(@PathVariable Long idGym, Model model) {
        model.addAttribute("gym", Gymrepository.findOne(idGym));
        model.addAttribute("sub", subRepository.findAll());
        // model.addAttribute("personalTrainer", PTRepository.findOne(idPt));
        // model.addAttribute("gyms", Gymrepository.findAll());
        return "infoGymForPersonalTrainers";
  }

  @RequestMapping(value="/userAccount/{idUser}/gymsForSubscription", method=RequestMethod.GET)
	public String gymListForSubscription(@PathVariable Long idUser, Model model) {
        model.addAttribute("user", userRepository.findOne(idUser));
        model.addAttribute("gyms", Gymrepository.findAll());
        return "gymsForSubscription";
  }

  
  // @RequestMapping(value="/gymsForSubscription/{id}", method=RequestMethod.GET)
	// public String gymListForSubscription(@PathVariable Long id, Model model) {
  //       model.addAttribute("user", userRepository.findOne(id));
  //       model.addAttribute("gyms", Gymrepository.findAll());
  //       return "gymsForSubscription";
  // }

  @RequestMapping(value="/gym/{idGym}/{idSub}/{idUser}/buySubscription")
    public String buySubscription(@PathVariable Long idGym, @PathVariable Long idSub, 
    @PathVariable Long idUser, Model model){
        model.addAttribute("gym", Gymrepository.findOne(idGym));
        model.addAttribute("user", userRepository.findOne(idUser));
        model.addAttribute("sub", subRepository.findOne(idSub));
        User user = userRepository.findOne(idUser);
        Subscription sub = subRepository.findOne(idSub);
        user.setSubscription(sub);
        userRepository.save(user);
        return "redirect:/userAccount/{idUser}/myProfile";
    }

    // modificare contains equals per i diversi campi

  @RequestMapping(value="/searchGym/{idPt}", method=RequestMethod.GET)
    public String gymSearch(@RequestParam String name, @PathVariable Long idPt, Model model) {
      List<Gym> gyms = (List<Gym>) Gymrepository.findAll();
      List<Gym> findGym = new ArrayList<Gym>();
      for(Gym gym : gyms) {
        if(gym.getName().toLowerCase().contains(name.toLowerCase()) ||
            gym.getAddress().toLowerCase().contains(name.toLowerCase()) ||
            gym.getCity().toLowerCase().contains(name.toLowerCase()) ||
            gym.getEmail().toLowerCase().contains(name.toLowerCase())){
          findGym.add(gym);
        }
        else {
          model.addAttribute("gyms", Gymrepository.findByName(name));
        }
        model.addAttribute("gyms", findGym);
        model.addAttribute("action", "searchGymFromPT");
        model.addAttribute("personalTrainer", PTRepository.findOne(idPt));
      }
      
      return "gyms";
    }

    @RequestMapping(value="/searchGymUser/{idUser}", method=RequestMethod.GET)
    public String gymSearchFromUser(@RequestParam String name, @PathVariable Long idUser, Model model) {
      List<Gym> gyms = (List<Gym>) Gymrepository.findAll();
      List<Gym> findGym = new ArrayList<Gym>();
      for(Gym gym : gyms) {
        if(gym.getName().toLowerCase().contains(name.toLowerCase()) ||
            gym.getAddress().toLowerCase().contains(name.toLowerCase()) ||
            gym.getCity().toLowerCase().contains(name.toLowerCase()) ||
            gym.getEmail().toLowerCase().contains(name.toLowerCase())){
          findGym.add(gym);
        }
        else {
          model.addAttribute("gyms", Gymrepository.findByName(name));
        }
        model.addAttribute("gyms", findGym);
        model.addAttribute("action", "searchGymFromUser");
        model.addAttribute("user", userRepository.findOne(idUser));
      }
      
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
            @RequestParam String password,
            Model model) {
        Gym newGym = new Gym();
        newGym.setPassword(password);
        newGym.setName(name);
        newGym.setAddress(address);
        newGym.setCivicNumber(civicNumber);
        newGym.setCity(city);
        newGym.setEmail(email);
        newGym.setPhoneNumber(phoneNumber);
        Gymrepository.save(newGym);

        //da sistemare qui sotto
        model.addAttribute("gyms", newGym);
        return "redirect:/";
  }

  @RequestMapping(value="/gymAccount/{id}/insertAffiliate", method=RequestMethod.GET)
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
    return "redirect:/gymAccount/{idGymAffiliate}/myProfile";
  }

}