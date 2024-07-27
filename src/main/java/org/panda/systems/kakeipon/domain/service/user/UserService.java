package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.app.user.UserInfo;
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

  public Long getMaxUserId( ) {
    return userRepository.getMaxUserId( );
  }

  public User save( UserInfo info ) {
    User user = new User( info );
    System.out.println( "before UserService User: " + user.toString() );
    userRepository.saveAndFlush( user );
    System.out.println( "after UserService User: " + user.toString() );
    return user;
  }
}
