package com.example.assignment3.Controller;

import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.PersonalTrainer;
import com.example.assignment3.Entities.User;
import com.example.assignment3.Repository.GymRepository;
import com.example.assignment3.Repository.PersonalTrainerRepository;
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
public class PersonalController {

  @Autowired
  PersonalTrainerRepository PTRepository;

  @Autowired
  GymRepository gymRepository;

  @Autowired
  UserRepository userRepository;

  @RequestMapping(value="/personalTrainers", method=RequestMethod.GET)
	public String userList(Model model) {
        model.addAttribute("personalTrainers", PTRepository.findAll());
        return "personalTrainers";
  }

  @RequestMapping(value="/personalTrainersContact/{id}", method=RequestMethod.GET)
	public String personalTrainerListForUserContact(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));
        model.addAttribute("personalTrainers", PTRepository.findAll());
        return "personalTrainersContact";
  }

  @RequestMapping("/personalTrainer/{id}")
  public String user(@PathVariable Long id, Model model) {
         model.addAttribute("personalTrainer", PTRepository.findOne(id));
         return "infoPersonalTrainer";
  }


  @RequestMapping("/insertPersonalTrainer")
  public String insertPersonalTrainer() {
    return "insertPersonalTrainer";
  }

  @RequestMapping(value="/insertPersonalTrainer", method=RequestMethod.POST)
	public String PersonalTrainerAdd(
            @RequestParam String name, @RequestParam String surname, @RequestParam Date birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String patent, @RequestParam String level, @RequestParam String email, @RequestParam String phoneNumber, 
            Model model) {

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
        
        PTRepository.save(newPersonalTrainer);

        return "redirect:/personalTrainers/";
  }


  @RequestMapping("/subscribePersonalTrainer/{idGym}/{idPT}")
    public String connectPtToGyms(@PathVariable Long idPT, @PathVariable Long idGym, Model model) {
      Gym gym = gymRepository.findOne(idGym);
      PersonalTrainer pt = PTRepository.findOne(idPT);

      //a personal trainer can't subscribe to the same gym more than one time
      if(!gym.getPersonalTrainers().contains(pt)){
        gym.getPersonalTrainers().add(pt);
        pt.getGyms().add(gym);
      };

      PTRepository.save(pt);
      gymRepository.save(gym);

      return "redirect:/personalTrainers";
    }

    @RequestMapping("/contactPt/{idUser}/{idPT}")
    public String connectPtToUser(@PathVariable Long idUser, @PathVariable Long idPT, Model model) {
      User user = userRepository.findOne(idUser);
      PersonalTrainer pt = PTRepository.findOne(idPT);

      //a personal trainer can't subscribe to the same gym more than one time
      if(user.getPersonalTrainer() == null ){
        user.setPersonalTrainer(pt);
        pt.getUsers().add(user);
      };

      PTRepository.save(pt);
      userRepository.save(user);

      return "redirect:/personalTrainers";
    }

}