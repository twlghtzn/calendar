package com.twlghtzn.calendar.security.verification;

import com.twlghtzn.calendar.security.JwtUtils;
import com.twlghtzn.calendar.security.LoginRequest;
import com.twlghtzn.calendar.security.LoginResponse;
import com.twlghtzn.calendar.user.User;
import com.twlghtzn.calendar.user.UserRepository;
import com.twlghtzn.calendar.user.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

  private JwtUtils jwtUtils;
  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private UserService userService;

  @Value("${GUEST_EMAIL}")
  private String guestEmail;

  @Value("${CALENDAR_USER_PASSWORD")
  private String password;

  @Autowired
  public VerificationService(JwtUtils jwtUtils,
                             AuthenticationManager authenticationManager,
                             UserRepository userRepository, UserService userService) {
    this.jwtUtils = jwtUtils;
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.userService = userService;
  }

  public String createVerificationToken(String username) {
    String password = userService.getPassword();
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return jwtUtils.generateJwtToken(authentication);
  }

  public String enableUser(String verificationToken) {
    String username = jwtUtils.getUserNameFromJwtToken(verificationToken);
      User user = getUser(username);
    userService.updateUser(user);
    return username;
  }

  public LoginRequest createLoginRequest(String username) {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setUsername(username);
    loginRequest.setPassword(password);
    return loginRequest;
  }

  public LoginResponse loginUser(LoginRequest loginRequest) {
    String jwt = createVerificationToken(loginRequest.getUsername());
    User user = getUser(loginRequest.getUsername());
    long id = user.getId();
    return new LoginResponse(jwt, id);
  }

  public User getUser(String username) {
    Optional<User> optionalUser = userRepository.findByUsername(username);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return user;
    } else {
      throw new RuntimeException();
    }
  }
}
