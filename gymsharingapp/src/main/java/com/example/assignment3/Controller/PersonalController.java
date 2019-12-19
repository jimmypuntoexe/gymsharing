package com.example.assignment3.Controller;

import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.PersonalTrainer;
import com.example.assignment3.Repository.GymRepository;
import com.example.assignment3.Repository.PersonalTrainerRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PersonalController {

  @Autowired
  PersonalTrainerRepository PTRepository;

  @Autowired
  GymRepository gymRepository;

  @RequestMapping(value="/personalTrainers", method=RequestMethod.GET)
	public String userList(Model model) {
        model.addAttribute("personalTrainers", PTRepository.findAll());
        return "personalTrainers";
  }

  @RequestMapping("/personalTrainer/{id}")
  public String user(@PathVariable Long id, Model model) {
         model.addAttribute("personalTrainer", PTRepository.findOne(id));
         return "infoPersonalTrainer";
  }

  // @RequestMapping("/gym/{id}/insertPersonalTrainer")
  // public String insertPersonalTrainer(@PathVariable Long id, Model model) {
  //   model.addAttribute("gym", gymRepository.findOne(id));
  //   return "insertPersonalTrainer";
  // }

  @RequestMapping("/insertPersonalTrainer")
  public String insertPersonalTrainer() {
    //model.addAttribute("gym", gymRepository.findOne(id));
    return "insertPersonalTrainer";
  }

  @RequestMapping(value="/insertPersonalTrainer", method=RequestMethod.POST)
	public String PersonalTrainerAdd(
            @RequestParam String name, @RequestParam String surname, @RequestParam Date birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String patent, @RequestParam String level, @RequestParam String email, @RequestParam String phoneNumber, 
            Model model) {

        //Gym gym = gymRepository.findOne(id);
        PersonalTrainer newPersonalTrainer = new PersonalTrainer();
        newPersonalTrainer.setName(name);
        newPersonalTrainer.setSurname(surname);
        newPersonalTrainer.setBirthDate(birthDate);
        newPersonalTrainer.setAge(age);
        newPersonalTrainer.setCF(CF);
        newPersonalTrainer.setPatent(patent);
        newPersonalTrainer.setLevel(level);
        newPersonalTrainer.setEmail(email);
        newPersonalTrainer.setPhoneNumber(phoneNumber);
        //newPersonalTrainer.setIdGym(id);

        // gym.getPersonalTrainers().add(newPersonalTrainer);
              
        // List<Gym> list = new ArrayList<>();
        // if (newPersonalTrainer.getGyms() == null){
        //   list.add(gym);
        // }
        // else {
        //   list = newPersonalTrainer.getGyms();
        //   list.add(gym);
        // }

        // newPersonalTrainer.setGyms(list);
        PTRepository.save(newPersonalTrainer);
        //PTRepository.findOne(newPersonalTrainer.getId()).getGyms().add(gym);

        //model.addAttribute("personalTrainer", gym);
        //model.addAttribute("gym", PTRepository.findAll());

        return "redirect:/personalTrainers/";
  }


  @RequestMapping("/subscribePersonalTrainer/{idGym}/{idPT}")
    public String connectPtToGyms(@PathVariable Long idPT, @PathVariable Long idGym, Model model) {
      Gym gym = gymRepository.findOne(idGym);
      PersonalTrainer pt = PTRepository.findOne(idPT);

      gym.getPersonalTrainers().add(pt);
      pt.getGyms().add(gym);

      PTRepository.save(pt);
      gymRepository.save(gym);

      //model.addAttribute("personalTrainer", PTRepository.findOne(id));
      return "redirect:/personalTrainers";
    }

  // @RequestMapping(value="/gym/{id}/insertPersonalTrainer", method=RequestMethod.POST)
	// public String PersonalTrainerAdd(@PathVariable Long id,
  //           @RequestParam String name, @RequestParam String surname, @RequestParam Date birthDate, @RequestParam String age,
  //           @RequestParam String CF, @RequestParam String patent, @RequestParam String level, @RequestParam String email, @RequestParam String phoneNumber, 
  //           Model model) {

  //       Gym gym = gymRepository.findOne(id);
  //       PersonalTrainer newPersonalTrainer = new PersonalTrainer();
  //       newPersonalTrainer.setName(name);
  //       newPersonalTrainer.setSurname(surname);
  //       newPersonalTrainer.setBirthDate(birthDate);
  //       newPersonalTrainer.setAge(age);
  //       newPersonalTrainer.setCF(CF);
  //       newPersonalTrainer.setPatent(patent);
  //       newPersonalTrainer.setLevel(level);
  //       newPersonalTrainer.setEmail(email);
  //       newPersonalTrainer.setPhoneNumber(phoneNumber);
  //       newPersonalTrainer.setIdGym(id);

  //       gym.getPersonalTrainers().add(newPersonalTrainer);
              
  //       List<Gym> list = new ArrayList<>();
  //       if (newPersonalTrainer.getGyms() == null){
  //         list.add(gym);
  //       }
  //       else {
  //         list = newPersonalTrainer.getGyms();
  //         list.add(gym);
  //       }

  //       newPersonalTrainer.setGyms(list);
  //       PTRepository.save(newPersonalTrainer);
  //       //PTRepository.findOne(newPersonalTrainer.getId()).getGyms().add(gym);

  //       //model.addAttribute("personalTrainer", gym);
  //       //model.addAttribute("gym", PTRepository.findAll());

  //       return "redirect:/gym/" + gym.getId();
  // }

}