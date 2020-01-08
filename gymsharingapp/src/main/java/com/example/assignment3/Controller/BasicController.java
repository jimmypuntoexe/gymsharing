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
public class BasicController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  GymRepository gymRepository;

  @Autowired
  PersonalTrainerRepository ptRepository;

    @RequestMapping("/")
    public String home() {
      return "login";
    }

    @RequestMapping(value="/userAccount/{idUser}", method=RequestMethod.GET)
    public String homeUser(@PathVariable Long idUser, Model model) {
      model.addAttribute("user", userRepository.findOne(idUser));
      return "userPage";
    }

    @RequestMapping(value="/gymAccount/{idGym}", method=RequestMethod.GET)
    public String homeGym(@PathVariable Long idGym, Model model) {
      model.addAttribute("gym", userRepository.findOne(idGym));
      return "gymPage";
    }

    @RequestMapping(value="/personalTrainerAccount/{idPt}", method=RequestMethod.GET)
    public String homePt(@PathVariable Long idPt, Model model) {
      model.addAttribute("personalTrainer", userRepository.findOne(idPt));
      return "personalTrainerPage";
    }

    @RequestMapping("/gymPage")
    public String gymPage() {
      return "gymPage";
    }

    // @RequestMapping("/login")
    // public String loginPage() {
    //   return "login";
    // }

    @RequestMapping("/userPage")
    public String userPage() {
      return "userPage";
    }

    @RequestMapping("/personalTrainerPage")
    public String personalTrainerPage() {
      return "personalTrainerPage";
    }


    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String Login(@RequestParam String username, @RequestParam String password, @RequestParam String type, Model model) {
            if (type.equals("user")){
              User user = userRepository.findByUsername(username);
              if (user.getPassword().equals(password)){
                return "redirect:/userAccount/" + user.getId();
              }
              else{
                return "redirect:/";
              }
            }
            if (type.equals("gym")){
              Gym gym = gymRepository.findByName(username);
              if (gym.getPassword().equals(password)) {
                return "redirect:/gymAccount/" + gym.getId();
              }
              else{
                return "redirect:/";
              }
            }
            if (type.equals("pt")){
              PersonalTrainer pt = ptRepository.findByUsername(username);
              if (pt.getPassword().equals(password)) {
                return "redirect:/personalTrainerAccount/" + pt.getId();
              }
              else{
                return "redirect:/";
              }
            }
            else{
              return "redirect:/";
            }
    }
}

