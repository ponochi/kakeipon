package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long userId);
}
