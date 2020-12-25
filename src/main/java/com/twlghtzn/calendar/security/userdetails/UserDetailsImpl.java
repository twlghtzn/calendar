package com.twlghtzn.calendar.security.userdetails;

import com.twlghtzn.calendar.user.User;
import java.util.Collection;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  private long id;
  private String username;
  private String password;
  private String email;
  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  public static UserDetailsImpl build(User user) {
    return new UserDetailsImpl(
        user.getUsername(),
        user.getPassword(),
        user.getEmail());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    UserDetailsImpl user = (UserDetailsImpl) object;
    return Objects.equals(id, user.id);
  }
}
