package com.example.assignment3.Controller;

import com.example.assignment3.Entities.*;
import com.example.assignment3.Repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  SubscriptionRepository subRepository;

  @Autowired
  PersonalTrainerRepository ptRepository;


  @RequestMapping("/insertUser")
  public String insertUser(Model model) {
    model.addAttribute("action", "insert");
    return "insertUser";
  }

  @RequestMapping(value="/deleteUser/{idUser}", method=RequestMethod.GET)
	public String userDelete(@PathVariable Long idUser) {

    User user = userRepository.findOne(idUser);
    PersonalTrainer pt = user.getPersonalTrainer();
    Subscription sub = user.getSubscriptions();

    if (sub != null){
      //user.setSubscription(null);
      sub.getUsers().remove(user);
      subRepository.save(sub);
    }

    if (pt != null){
      //user.setPersonalTrainer(null);
      pt.getUsers().remove(user);
      ptRepository.save(pt);
    }

    userRepository.delete(idUser);

    return "redirect:/";
  }

  @RequestMapping("/userAccount/{idUser}/myProfile")
 public String user(@PathVariable Long idUser, Model model) {
        model.addAttribute("user", userRepository.findOne(idUser));
        return "infoUser";
 }
 
  @RequestMapping(value="/users", method=RequestMethod.GET)
	public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
  }

  @RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public String UserAdd(
            @RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String address, @RequestParam String civicNumber,
            @RequestParam String city, @RequestParam String email, @RequestParam String phoneNumber,
            @RequestParam String username, @RequestParam String password,
            Model model) {
        String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setSurname(surname);
        if(birthDate.matches(regex)){
          newUser.setBirthDate(birthDate);
        }
        else {
          model.addAttribute("action", "dateErrorInsertUser");
          return "error";
        }
        
        newUser.setAge(age);
        newUser.setCF(CF);
        newUser.setAddress(address);
        newUser.setCivicNumber(civicNumber);
        newUser.setCity(city);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);
        userRepository.save(newUser);

        //da sistemare qui sotto
        model.addAttribute("user", newUser);
        return "redirect:/";
  }
  

  @RequestMapping(value="/modifyUser/{id}", method=RequestMethod.GET)
  public String updateUser( @PathVariable Long id, Model model) {
          User user = userRepository.findOne(id);
          model.addAttribute("action", "update");
          model.addAttribute("user", user);
          return "insertUser";
  }
  

  @RequestMapping(value="/updateUser/{id}", method=RequestMethod.GET)
	public String UserUpdate(@PathVariable Long id,
            @RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String address, @RequestParam String civicNumber,
            @RequestParam String city, @RequestParam String email, @RequestParam String phoneNumber, 
            Model model) {
        String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        User user = userRepository.findOne(id);
        user.setName(name);
        user.setSurname(surname);
        if(birthDate.matches(regex)){
          user.setBirthDate(birthDate);
        }
        else {
          model.addAttribute("errorAction", "dateErrorUpdatetUser");
          model.addAttribute("user", user);
          return "error";
        }
        user.setAge(age);
        user.setCF(CF);
        user.setAddress(address);
        user.setCivicNumber(civicNumber);
        user.setCity(city);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);

        //da sistemare qui sotto
        model.addAttribute("user", user);
        return "redirect:/userAccount/{id}/myProfile";
  }
  @RequestMapping(value="/{idUser}/deleteSubScription", method=RequestMethod.GET)
	public String subDelete(Model model, @PathVariable Long idUser) {
        model.addAttribute("user", userRepository.findOne(idUser));
        User user = userRepository.findOne(idUser);
        Subscription sub = subRepository.findOne(user.getSubscriptions().getId());

        sub.getUsers().remove(user);

        user.setSubscription(null);

        userRepository.save(user);
        return "redirect:/userAccount/{idUser}/myProfile";
  }

  @RequestMapping(value="/searchUser", method=RequestMethod.GET)
    public String userSearch(@RequestParam String name, Model model) {
          model.addAttribute("users", userRepository.findByName(name));
          return "users";
    }



}