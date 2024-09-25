package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {

//  @Override
//  Collection<? extends GrantedAuthority> findAll();

  Authorities findById(Integer id);

  Authorities findByUsername(String username);

  @Override
  Authorities saveAndFlush(Authorities entity);
}
