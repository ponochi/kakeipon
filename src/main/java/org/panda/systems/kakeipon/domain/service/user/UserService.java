package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.app.user.RoleForm;
import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class UserService {
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleService roleService;

  // Default Constructor
  public UserService() {

  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long userId) {
    return userRepository.findByUserAndRoleKeyId(userId);
  }

  public Long getMaxUserId() {
    return userRepository.getMaxUserId();
  }

  @Transactional
  public User saveUser(User user) {
    return userRepository.saveAndFlush(user);
  }

  public List<UserForm> findAllUserForm() {
    List<User> users = userRepository.findAll();

    List<UserForm> userForms = new ArrayList<>();
    for (User user : users) {
      UserForm userForm = new UserForm();
      userForm.setUserId(user.getUserId());
      userForm.setNickName(user.getNickName());
      userForm.setLastName(user.getLastName());
      userForm.setFirstName(user.getFirstName());
      userForm.setPassword(user.getPassword());
      userForm.setEmail(user.getEmail());
      userForm.setBirthDay(user.getBirthDay());
      userForm.setPhoneNumber(user.getPhoneNumber());
      userForm.setRoleId(user.getRoleId());
      userForm.setRoleForm(new RoleForm());
      userForm.getRoleForm().setRoleId(user.getRoleId());
      userForm.getRoleForm().setRoleName(
          roleService.findByRoleId(user.getRoleId()).getRoleName());
      userForms.add(userForm);
    }
    return userForms;
  }
}
