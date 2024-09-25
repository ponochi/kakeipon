package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.app.user.UserExtForm;
import org.panda.systems.kakeipon.app.user.UserExtForm;
import org.panda.systems.kakeipon.domain.model.user.UserExt;
import org.panda.systems.kakeipon.domain.repository.user.UserExtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserExtService implements Serializable {
  @Autowired
  public UserExtRepository userExtRepository;

  public List<UserExt> findAll() {
    return userExtRepository.findAll();
  }

  public UserExt findById(Integer id) {
    return userExtRepository.findById(id).orElse(null);
  }

  public UserExtForm findByIdToForm(Integer id) {
    UserExtForm userExtForm = new UserExtForm();

    UserExt userExt = userExtRepository.findById(id).orElse(null);

    if (userExt != null) {
      userExtForm.setId(userExt.getId());
      userExtForm.setLastName(userExt.getLastName());
      userExtForm.setFirstName(userExt.getFirstName());
      userExtForm.setEmail(userExt.getEmail());
      userExtForm.setBirthDay(userExt.getBirthDay());
      userExtForm.setPhoneNumber(userExt.getPhoneNumber());
      userExtForm.setEntryDate(userExt.getEntryDate());
      userExtForm.setUpdateDate(userExt.getUpdateDate());
      return userExtForm;
    }
    return null;
  };

  public Integer getMaxId() {
    return userExtRepository.getMaxId();
  }

  @Transactional
  public UserExt saveUserExt(UserExt userExt) {
    return userExtRepository.saveAndFlush(userExt);
  }

  public List<UserExtForm> findAllUserForm() {
    List<UserExt> usersExt = userExtRepository.findAll();

    List<UserExtForm> userExtForms = new ArrayList<>();
    for (UserExt userExt : usersExt) {
      UserExtForm userExtForm = new UserExtForm();
      userExtForm.setId(userExt.getId());
      userExtForm.setLastName(userExt.getLastName());
      userExtForm.setFirstName(userExt.getFirstName());
      userExtForm.setEmail(userExt.getEmail());
      userExtForm.setBirthDay(userExt.getBirthDay());
      userExtForm.setPhoneNumber(userExt.getPhoneNumber());
      userExtForms.add(userExtForm);
    }
    return userExtForms;
  }
}
