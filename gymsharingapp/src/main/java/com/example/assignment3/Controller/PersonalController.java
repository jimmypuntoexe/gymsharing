package com.example.assignment3.Controller;

import com.example.assignment3.Entities.PersonalTrainer;
import com.example.assignment3.Repository.PersonalTrainerRepository;

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
  PersonalTrainerRepository repository;


}