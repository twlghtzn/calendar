package com.twlghtzn.calendar.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  @Value("${DEFAULT_USER_PASSWORD}")
  private String password;
  private String email;
  private String avatarURL;

  public User(String username, String email, String avatarURL) {
    this.username = username;
    this.email = email;
    this.avatarURL = avatarURL;
  }
}
