package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.repository.user.UserRepository;
import org.panda.systems.kakeipon.domain.repository.user.UsersDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDetailsService implements UserDetailsService {
  @Autowired
  UsersDetailsRepository usersDetailsRepository;
  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    User user = userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException(username + " is not found."));

    return new UsersDetails(user);
  }

  public List<UsersDetails> findAllUsersToForm() {
    List<User> users = usersDetailsRepository.findAll();
    List<UsersDetails> usersDetails = new ArrayList<>();
    for (User user : users) {
      usersDetails.add(new UsersDetails(user));
    }
    return usersDetails;
  }

  public UsersDetails findByUserId(Long userId) {
    return usersDetailsRepository.findByUserId(userId);
  }

  public UsersDetails findById(String username) {
    User user = userRepository.findById(username).orElse(null);
    return new UsersDetails(user);

  }

  public boolean existsById(String id) {
    return userRepository.existsById(id);
  }

  public User saveAndFlush(User entity) {
    return userRepository.saveAndFlush(entity);
  }

}
