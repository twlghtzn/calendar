package com.twlghtzn.calendar.welcome;

import com.twlghtzn.calendar.security.LoginRequest;
import com.twlghtzn.calendar.security.LoginResponse;
import com.twlghtzn.calendar.security.verification.VerificationService;
import com.twlghtzn.calendar.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  private UserService userService;
  private VerificationService verificationService;

  @Value("${CALENDAR_USER_PASSWORD")
  private String password;

  @Autowired
  public MainController(UserService userService, VerificationService verificationService) {
    this.userService = userService;
    this.verificationService = verificationService;
  }

  @GetMapping("/")
  public String main(Model model, @RequestParam(name = "info", required = false) String info,
                     @RequestParam(name = "username", required = false) String username) {
    if (info == null) {
      model.addAttribute("info", "signup");
    } else {
      model.addAttribute("info", info);
    }
    if (info != null && username != null && info.equals("unverified")) {
      String verificationToken = verificationService.createVerificationToken(username);
      model.addAttribute("verificationToken", verificationToken);
    }
    if (info != null && username != null && info.equals("verified")) {
      model.addAttribute("username", username);
      model.addAttribute("password", password);
    }
    return "signup";
  }

  @PostMapping("/signup")
  public String signup(@RequestParam(name = "signupUsername") String signupUsername,
                       @RequestParam(name = "email") String email) {
    userService.saveUser(signupUsername, email);
    return "redirect:/?info=unverified&username=" + signupUsername;
  }

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String login(LoginRequest loginRequest) {
    LoginResponse loginResponse = verificationService.loginUser(loginRequest);
    return "redirect:/?info=pass";
  }

  @PostMapping("/enter")
  public String enter(@RequestParam(name = "guestUsername") String guestUsername) {
    userService.saveGuest(guestUsername);

    return "redirect:/?info=guest";
  }
}
