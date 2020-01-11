package com.example.assignment3.Controller;

import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Entities.PersonalTrainer;
import com.example.assignment3.Entities.User;
import com.example.assignment3.Repository.GymRepository;
import com.example.assignment3.Repository.PersonalTrainerRepository;
import com.example.assignment3.Repository.UserRepository;

import java.util.ArrayList;
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
        gymRepository.save(gym);
    }
    for(User user : users){
      user.setPersonalTrainer(null);
      userRepository.save(user);
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

    return "redirect:/userAccount/{idUser}/myProfile";
    }

  @RequestMapping("/editPersonalTrainer/{id}")
  public String editPersonalTrainer(@PathVariable Long id, Model model) {
    model.addAttribute("action", "edit");
    model.addAttribute("personal", PTRepository.findOne(id));
    return "insertPersonalTrainer";
  }
  @RequestMapping(value="/updatePersonalTrainer/{id}", method=RequestMethod.GET)
	public String PersonalTrainerUpdate(@PathVariable Long id,
            @RequestParam String name, @RequestParam String surname, 
            @RequestParam String birthDate, @RequestParam String age,
            @RequestParam String CF, 
            @RequestParam String patent, @RequestParam String level, 
            @RequestParam String email, @RequestParam String phoneNumber, 
            Model model) {

        String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        PersonalTrainer personalTrainer = PTRepository.findOne(id);
        personalTrainer.setName(name);
        personalTrainer.setSurname(surname);

        if(birthDate.matches(regex)){
          personalTrainer.setBirthDate(birthDate);
        }
        else {
          model.addAttribute("errorAction", "dateErrorUpdatePersonalTrainer");
          model.addAttribute("personalTrainer", personalTrainer);
          return "error";
        }

        personalTrainer.setAge(age);
        personalTrainer.setCF(CF);
        personalTrainer.setPatent(patent);
        personalTrainer.setLevel(level);
        personalTrainer.setEmail(email);
        personalTrainer.setPhoneNumber(phoneNumber);
        
        PTRepository.save(personalTrainer);
        model.addAttribute("personalTrainer", personalTrainer);

        return "redirect:/personalTrainerAccount/{id}/myProfile";
  }



  @RequestMapping(value="/insertPersonalTrainer", method=RequestMethod.GET)
	public String PersonalTrainerAdd(
            @RequestParam String name, @RequestParam String surname, @RequestParam String birthDate, @RequestParam String age,
            @RequestParam String CF, @RequestParam String patent, @RequestParam String level, @RequestParam String email, @RequestParam String phoneNumber, 
            @RequestParam String username,  @RequestParam String password,
            Model model) {

        String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        PersonalTrainer newPersonalTrainer = new PersonalTrainer();
        newPersonalTrainer.setUsername(username);
        newPersonalTrainer.setPassword(password);
        newPersonalTrainer.setName(name);
        newPersonalTrainer.setSurname(surname);
        if(birthDate.matches(regex)){
          newPersonalTrainer.setBirthDate(birthDate);
        }
        else {
          model.addAttribute("action", "dateErrorInsertPersonalTrainer");
          return "error";
        }
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

    @RequestMapping(value="/searchPersonalTGym/{idGym}", method=RequestMethod.GET)
    public String ptSearchFromGym(@RequestParam String name, @PathVariable Long idGym, Model model) {
      List<PersonalTrainer> personalTrainers = (List<PersonalTrainer>) PTRepository.findAll();
      List<PersonalTrainer> findPt = new ArrayList<PersonalTrainer>();
      for(PersonalTrainer pt : personalTrainers) {
        if(pt.getName().toLowerCase().contains(name.toLowerCase()) ||
            pt.getSurname().toLowerCase().contains(name.toLowerCase()) ||
            pt.getPatent().toLowerCase().contains(name.toLowerCase()) ||
            pt.getLevel().toLowerCase().contains(name.toLowerCase()) ||
            pt.getEmail().toLowerCase().contains(name.toLowerCase())){
          findPt.add(pt);
        }
        else {
          model.addAttribute("personalTrainers", PTRepository.findByName(name));
        }
        model.addAttribute("personalTrainers", findPt);
        model.addAttribute("action", "searchPtFromGym");
        model.addAttribute("gym", gymRepository.findOne(idGym));
      }
          
          return "personalTrainers";
    }

    @RequestMapping(value="/searchPersonalTUser/{idUser}", method=RequestMethod.GET)
    public String ptSearchFromUser(@RequestParam String name, @PathVariable Long idUser, Model model) {
      List<PersonalTrainer> personalTrainers = (List<PersonalTrainer>) PTRepository.findAll();
      List<PersonalTrainer> findPt = new ArrayList<PersonalTrainer>();
      for(PersonalTrainer pt : personalTrainers) {
        if(pt.getName().toLowerCase().contains(name.toLowerCase()) ||
            pt.getSurname().toLowerCase().contains(name.toLowerCase()) ||
            pt.getPatent().toLowerCase().contains(name.toLowerCase()) ||
            pt.getLevel().toLowerCase().contains(name.toLowerCase()) ||
            pt.getEmail().toLowerCase().contains(name.toLowerCase())){
          findPt.add(pt);
        }
        else {
          model.addAttribute("personalTrainers", PTRepository.findByName(name));
        }
        model.addAttribute("personalTrainers", findPt);
        model.addAttribute("action", "searchPtFromUser");
        model.addAttribute("user", userRepository.findOne(idUser));
      }
          
          return "personalTrainers";
    }

}