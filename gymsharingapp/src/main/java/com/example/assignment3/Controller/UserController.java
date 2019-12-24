package com.example.assignment3.Controller;

import com.example.assignment3.Entities.User;
import com.example.assignment3.Repository.UserRepository;
import com.example.assignment3.Repository.SubscriptionRepository;

import java.sql.Date;

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
  UserRepository repository;

  @Autowired
  SubscriptionRepository subRepository;


  @RequestMapping("/insertUser")
  public String insertUser(Model model) {
    model.addAttribute("action", "insert");
    return "insertUser";
  }

  @RequestMapping(value="/deleteUser/{id}", method=RequestMethod.GET)
	public String userDelete(@PathVariable Long id) {
    repository.delete(id);
       return "redirect:/users/";
  }

  @RequestMapping("/user/{id}")
 public String user(@PathVariable Long id, Model model) {
        model.addAttribute("user", repository.findOne(id));
        return "infoUser";
 }
 
  @RequestMapping(value="/users", method=RequestMethod.GET)
	public String userList(Model model) {
        model.addAttribute("users", repository.findAll());
        return "users";
  }

  @RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public String UserAdd(
            @RequestParam String name, @RequestParam String surname, @RequestParam Date birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String address, @RequestParam String civicNumber,
            @RequestParam String city, @RequestParam String email, @RequestParam String phoneNumber, 
            Model model) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setBirthDate(birthDate);
        newUser.setAge(age);
        newUser.setCF(CF);
        newUser.setAddress(address);
        newUser.setCivicNumber(civicNumber);
        newUser.setCity(city);
        newUser.setEmail(email);
        newUser.setPhoneNumber(phoneNumber);
        repository.save(newUser);

        //da sistemare qui sotto
        model.addAttribute("user", newUser);
        return "redirect:/users/";
  }
  

  @RequestMapping(value="/modifyUser/{id}", method=RequestMethod.GET)
  public String updateUser( @PathVariable Long id, Model model) {
          User user = repository.findOne(id);
          model.addAttribute("action", "update");
          model.addAttribute("user", user);
          return "insertUser";
  }
  

  @RequestMapping(value="/updateUser/{id}", method=RequestMethod.GET)
	public String UserUpdate(@PathVariable Long id,
            @RequestParam String name, @RequestParam String surname, @RequestParam Date birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String address, @RequestParam String civicNumber,
            @RequestParam String city, @RequestParam String email, @RequestParam String phoneNumber, 
            Model model) {
        User user = repository.findOne(id);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthDate(birthDate);
        user.setAge(age);
        user.setCF(CF);
        user.setAddress(address);
        user.setCivicNumber(civicNumber);
        user.setCity(city);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        repository.save(user);

        //da sistemare qui sotto
        model.addAttribute("user", user);
        return "redirect:/users/";
  }
  @RequestMapping(value="/{idUser}/deleteSubScription", method=RequestMethod.GET)
	public String subDelete(Model model, @PathVariable Long idUser) {
        model.addAttribute("user", repository.findOne(idUser));
        User user = repository.findOne(idUser);
        user.setSubscription(null);
        repository.save(user);
        return "redirect:/user/" + idUser;
  }



}