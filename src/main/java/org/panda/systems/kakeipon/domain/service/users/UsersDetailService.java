package org.panda.systems.kakeipon.domain.service.users;

import org.panda.systems.kakeipon.domain.model.users.Users;
import org.panda.systems.kakeipon.domain.repository.users.UsersDetailRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersDetailService implements UserDetailsService {

  @SuppressWarnings("ClassEscapesDefinedScope")
  public final UsersDetailRepository usersDetailRepository;

  public UsersDetailService(
      @SuppressWarnings("ClassEscapesDefinedScope")
      UsersDetailRepository usersDetailRepository) {

    this.usersDetailRepository = usersDetailRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Users users = usersDetailRepository.findByUsername(username);
    if (users == null) {
      //noinspection ThrowableNotThrown
      new UsernameNotFoundException(username + " is not found.");
    }

    return new UsersDetail(users);
  }

  public List<UsersDetail> findAllUsersToForm() {
    List<Users> users = usersDetailRepository.findAll();
    List<UsersDetail> usersDetails = new ArrayList<>();
    for (Users user : users) {
      usersDetails.add(new UsersDetail(user));
    }
    return usersDetails;
  }

  public Users findByUserId(Long userId) {
    return usersDetailRepository.findByUserId(userId);
  }

  public UsersDetail findByUsername(String username) {
    Users users = usersDetailRepository.findByUsername(username);
    return new UsersDetail(users);

  }

  public boolean existsByUserId(Long userId) {

    return usersDetailRepository.existsByUserId(userId);
  }

  public boolean existsByUsername(String username) {

    return usersDetailRepository.existsByUsername(username);
  }

  public Users saveAndFlush(Users entity) {

    return usersDetailRepository.saveAndFlush(entity);
  }

}
