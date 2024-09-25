package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.panda.systems.kakeipon.domain.repository.user.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AuthoritiesService implements Serializable {
  @Autowired
  public AuthoritiesRepository authoritiesRepository;

  public List<Authorities> findAll() {
    return authoritiesRepository.findAll();
  }

  public Authorities findById(Integer id) {
    return authoritiesRepository.findById(id);
  }

  public Authorities findByUsername(String username) {
    return authoritiesRepository.findByUsername(username);
  }

  @Transactional
  public Authorities saveAuthorities(Authorities authorities) {
    return authoritiesRepository.saveAndFlush(authorities);
  }
}
