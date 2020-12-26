package com.twlghtzn.calendar.user;

import com.twlghtzn.calendar.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Value("${GUEST_EMAIL}")
  private String guestEmail;

  @Value("${CALENDAR_USER_PASSWORD")
  private String password;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void saveUser(String username, String email) {
    userRepository.save(new User(username, email, password));
  }

  public void updateUser(User user) {
    userRepository.save(user);
  }

  public void saveGuest(String username) {
    userRepository.save(new User(username, guestEmail, password));
  }

  public String getGuestEmail() {
    return guestEmail;
  }

  public String getPassword() {
    return password;
  }
}
