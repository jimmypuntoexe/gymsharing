package com.example.assignment3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

  @RequestMapping("/")
  public String home() {
    return "index";
  }

  @RequestMapping("/insertGym")
  public String gym() {
    return "insertGym";
  }

}