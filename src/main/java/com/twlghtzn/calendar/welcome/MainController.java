package com.twlghtzn.calendar.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @GetMapping("/")
  public String main() {
    return "index";
  }

  @PostMapping("/signup")
  public String signup(@RequestParam(name = "signupUsername") String signupUsername,
                       @RequestParam(name = "email") String email) {
    return "redirect:/?info=unverified";
  }
}
