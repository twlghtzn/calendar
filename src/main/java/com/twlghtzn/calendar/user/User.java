package com.twlghtzn.calendar.user;

import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private String email;
  private String avatarURL;
  private boolean enabled;

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    enabled = false;
  }
}
