package org.panda.systems.kakeipon.domain.service.users;

import org.panda.systems.kakeipon.app.users.UsersExtForm;
import org.panda.systems.kakeipon.domain.model.users.UsersExt;
import org.panda.systems.kakeipon.domain.repository.users.UsersExtRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class UsersExtService implements Serializable {

  public final UsersExtRepository usersExtRepository;

  public UsersExtService(UsersExtRepository usersExtRepository) {
    this.usersExtRepository = usersExtRepository;
  }

  public List<UsersExt> findAll() {
    return usersExtRepository.findAll();
  }

  public UsersExt findByUserId(Long userId) {
    return usersExtRepository.findByUserId(userId).orElse(null);
  }

  public UsersExtForm findByUserIdToForm(Long userId) {

    UsersExtForm usersExtForm = new UsersExtForm();

    UsersExt usersExt = usersExtRepository.findByUserId(userId).orElse(null);

    if (usersExt != null) {
      usersExtForm.setUserId(usersExt.getUserId());
      usersExtForm.setLastName(usersExt.getLastName());
      usersExtForm.setFirstName(usersExt.getFirstName());
      usersExtForm.setEmail(usersExt.getEmail());
      usersExtForm.setBirthDay(usersExt.getBirthDay());
      usersExtForm.setPhoneNumber(usersExt.getPhoneNumber());
      usersExtForm.setEntryDate(usersExt.getEntryDate());
      usersExtForm.setUpdateDate(usersExt.getUpdateDate());

      return usersExtForm;
    }
    return null;
  }

  ;

  public Integer getMaxId() {
    return usersExtRepository.getMaxId();
  }

  //    @Transactional
  public UsersExt saveUserExt(UsersExt usersExt) {
    return usersExtRepository.saveAndFlush(usersExt);
  }

  public List<UsersExtForm> findAllUserForm() {
    List<UsersExt> usersExt = usersExtRepository.findAll();

    List<UsersExtForm> usersExtForms = new ArrayList<>();
    for (UsersExt userExt : usersExt) {
      UsersExtForm usersExtForm = new UsersExtForm();
      usersExtForm.setUserId(userExt.getUserId());
      usersExtForm.setLastName(userExt.getLastName());
      usersExtForm.setFirstName(userExt.getFirstName());
      usersExtForm.setEmail(userExt.getEmail());
      usersExtForm.setBirthDay(userExt.getBirthDay());
      usersExtForm.setPhoneNumber(userExt.getPhoneNumber());

      usersExtForms.add(usersExtForm);
    }
    return usersExtForms;
  }
}
