package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository< User, Long > {
  User findByUserId( Long userId );

  @Query(value = "SELECT MAX(user_id) user_id FROM usr", nativeQuery = true)
  Long getMaxUserId( );

  User saveAndFlush( User user );
}
