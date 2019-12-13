package com.example.assignment3.Controller;

import com.example.assignment3.Entities.Gym;
import com.example.assignment3.Repository.GymRepository;

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
  GymRepository repository;

  // @RequestMapping("/")
  // public String home() {
  //   return "index";
  // }

  @RequestMapping("/insertGym")
  public String insertGym() {
    return "insertGym";
  }

  @RequestMapping(value="/deleteGym/{id}", method=RequestMethod.GET)
	public String gymDelete(@PathVariable Long id) {
       repository.delete(id);
       return "redirect:/gyms/";
  }
  
  @RequestMapping("/gym/{id}")
	public String gym(@PathVariable Long id, Model model) {
        model.addAttribute("gym", repository.findOne(id));
        return "infoGym";
	}

  @RequestMapping(value="/gyms", method=RequestMethod.GET)
	public String gymList(Model model) {
        model.addAttribute("gyms", repository.findAll());
        return "gyms";
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
        repository.save(newGym);

        //da sistemare qui sotto
        model.addAttribute("gyms", newGym);
        return "redirect:/gyms/";
	}

}