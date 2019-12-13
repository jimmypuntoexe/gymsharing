package com.example.assignment3.Controller;

import com.example.assignment3.Entities.User;
import com.example.assignment3.Repository.UserRepository;

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


  @RequestMapping("/insertUser")
  public String insertUser() {
    return "insertUser";
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

}