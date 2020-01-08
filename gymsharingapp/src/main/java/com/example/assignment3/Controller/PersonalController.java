package com.example.assignment3.Controller;

import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.PersonalTrainer;
import com.example.assignment3.Entities.User;
import com.example.assignment3.Repository.GymRepository;
import com.example.assignment3.Repository.PersonalTrainerRepository;
import com.example.assignment3.Repository.UserRepository;

import java.sql.Date;
import java.util.List;

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

  @RequestMapping(value="/userAccount/{idUser}/personalTrainersContact", method=RequestMethod.GET)
	public String personalTrainerListForUserContact(@PathVariable Long idUser, Model model) {
        model.addAttribute("user", userRepository.findOne(idUser));
        model.addAttribute("personalTrainers", PTRepository.findAll());
        return "personalTrainersContact";
  }

  @RequestMapping("/personalTrainerAccount/{idPt}/myProfile")
  public String personalTrainerProfile(@PathVariable Long idPt, Model model) {
         model.addAttribute("personalTrainer", PTRepository.findOne(idPt));
         return "infoPersonalTrainer";
  }
  
  @RequestMapping("/personalTrainerAccount/{idPt}/infoPersonalTrainerForUser")
  public String personalTrainerProfileForUser(@PathVariable Long idPt, Model model) {
         model.addAttribute("personalTrainer", PTRepository.findOne(idPt));
         return "infoPersonalTrainerForUser";
  }

  @RequestMapping("/insertPersonalTrainer")
  public String insertPersonalTrainer(Model model) {
    model.addAttribute("action", "insert");
    return "insertPersonalTrainer";
  }

  @RequestMapping(value="/removePersonalTrainer/{id}", method=RequestMethod.GET)
	public String ptrDelete(@PathVariable Long id) {
    PersonalTrainer ptr = PTRepository.findOne(id);
    List<Gym> gyms = ptr.getGyms();
    List<User> users = ptr.getUsers();
    for(Gym gym:gyms){
        gym.getPersonalTrainers().remove(ptr);
    }
    for(User user : users){
      user.setPersonalTrainer(null);
    }
    PTRepository.delete(id);
    return "redirect:/";
    }
  
  @RequestMapping(value="/removePT/{idUser}/{idPt}", method=RequestMethod.GET)
  public String ptDeleteFromUser(@PathVariable Long idPt, @PathVariable Long idUser) {
    PersonalTrainer ptr = PTRepository.findOne(idPt);
    User user = userRepository.findOne(idUser);
    user.setPersonalTrainer(null);
    ptr.getUsers().remove(user);

    userRepository.save(user);
    PTRepository.save(ptr);

    return "redirect:/userAccount/{idUser}";
    }

  @RequestMapping("/editPersonalTrainer/{id}")
  public String editPersonalTrainer(@PathVariable Long id, Model model) {
    model.addAttribute("action", "edit");
    model.addAttribute("personal", PTRepository.findOne(id));
    return "insertPersonalTrainer";
  }
  @RequestMapping(value="/updatePersonalTrainer/{id}", method=RequestMethod.GET)
	public String PersonalTrainerUpdate(@PathVariable Long id,
            @RequestParam (required = false) String name, @RequestParam (required = false) String surname, 
            @RequestParam (required = false) Date birthDate, @RequestParam (required = false) String age,
            @RequestParam (required = false) String CF, 
            @RequestParam (required = false) String patent, @RequestParam (required = false) String level, 
            @RequestParam (required = false) String email, @RequestParam (required = false) String phoneNumber, 
            Model model) {

        PersonalTrainer personalTrainer = PTRepository.findOne(id);
        personalTrainer.setName(name);
        personalTrainer.setSurname(surname);
        personalTrainer.setBirthDate(birthDate);
        personalTrainer.setAge(age);
        personalTrainer.setCF(CF);
        personalTrainer.setPatent(patent);
        personalTrainer.setLevel(level);
        personalTrainer.setEmail(email);
        personalTrainer.setPhoneNumber(phoneNumber);
        
        PTRepository.save(personalTrainer);
        model.addAttribute("personal", personalTrainer);

        return "redirect:/personalTrainers/";
  }



  @RequestMapping(value="/insertPersonalTrainer", method=RequestMethod.POST)
	public String PersonalTrainerAdd(
            @RequestParam String name, @RequestParam String surname, @RequestParam Date birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String patent, @RequestParam String level, @RequestParam String email, @RequestParam String phoneNumber, 
            @RequestParam String username,  @RequestParam String password,
            Model model) {

        PersonalTrainer newPersonalTrainer = new PersonalTrainer();
        newPersonalTrainer.setUsername(username);
        newPersonalTrainer.setPassword(password);
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

        return "redirect:/";
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

      return "redirect:/personalTrainerAccount/{idPT}/myProfile";
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

      return "redirect:/userAccount/{idUser}";
    }

    @RequestMapping(value="/searchPersonalT", method=RequestMethod.GET)
    public String userSearch(@RequestParam String name, Model model) {
          model.addAttribute("personalTrainers", PTRepository.findByName(name));
          return "personalTrainers";
    }

}