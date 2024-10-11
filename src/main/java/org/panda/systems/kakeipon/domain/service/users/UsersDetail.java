package org.panda.systems.kakeipon.domain.service.users;

import lombok.Data;
import org.panda.systems.kakeipon.domain.model.users.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Data
public class UsersDetail implements UserDetails {
  private final Users users;

  public UsersDetail(Users users) {
    this.users = users;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList("ROLE_" + this.users.getRoleName().name());
  }

  @Override
  public String getPassword() {
    return this.users.getPassword();
  }

  @Override
  public String getUsername() {
    return this.users.getId();
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
}
