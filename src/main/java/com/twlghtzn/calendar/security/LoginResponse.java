package com.twlghtzn.calendar.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponse {

  private String token;
  private String type;
  private Long id;

  public LoginResponse(String accessToken, Long id) {
    token = accessToken;
    type = "Bearer";
    this.id = id;
  }
}
