package com.example.assignment3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {
    @RequestMapping("/")
    public String home() {
      return "index";
    }

    @RequestMapping("/gymPage")
    public String gymPage() {
      return "gymPage";
    }

    @RequestMapping("/userPage")
    public String userPage() {
      return "userPage";
    }

    @RequestMapping("/personalTrainerPage")
    public String personalTrainerPage() {
      return "personalTrainerPage";
    }
}

