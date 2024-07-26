package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
  @Autowired
  UserRepository userRepository;

  // Default Constructor
  public UserService( ) {

  }

  public List< User > findAll( ) {
    return userRepository.findAll( );
  }

  public User findByUserId( Long userId ) {
    return userRepository.findByUserId( userId );
  }

  public User save( User user ) {
    userRepository.save( user );
    return user;
  }
}
