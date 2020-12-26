package com.twlghtzn.calendar.security.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VerificationController {

  private VerificationService verificationService;

  @Autowired
  public VerificationController(
      VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  @PostMapping("/verify/{verificationToken}")
  public String verify(@PathVariable("verificationToken") String verificationToken) {
    String username = verificationService.enableUser(verificationToken);
    return "redirect:/?info=verified&username=" + username;
  }
}
