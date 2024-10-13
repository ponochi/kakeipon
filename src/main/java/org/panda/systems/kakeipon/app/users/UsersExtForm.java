package org.panda.systems.kakeipon.app.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.users.UsersExt;
import org.panda.systems.kakeipon.domain.service.users.UsersExtService;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "users")
@Data
public class UsersExtForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final UsersExtService usersExtService;

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "first_name")
  private String firstName;

  @NotEmpty(message = "メールアドレスは必須です")
  @Column
  private String email;

  @Column(name = "birth_day")
  private LocalDateTime birthDay;

  @NotEmpty(message = "電話番号は必須です")
  @Column(name = "phone_number")
  private String phoneNumber;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  // Default constructor
  public UsersExtForm() {

    this.usersExtService = null;
  }

  public UsersExtForm(UsersExtService usersExtService) {

    this.usersExtService = usersExtService;
  }

  public UsersExtForm setUserExtFormByDB(Long userId) {

    if (userId == null) {
//      this.setUsername(Integer.parseInt("1"));
    } else {
      this.setUserId(userId);
    }

    UsersExt usersExt = null;
    if (usersExtService != null) {
      usersExt = usersExtService.findByUserId(this.getUserId());
    }

    if (usersExt != null) {
      this.setLastName(usersExt.getLastName());
      this.setFirstName(usersExt.getFirstName());
      this.setEmail(usersExt.getEmail());
      this.setBirthDay(usersExt.getBirthDay());
      this.setPhoneNumber(usersExt.getPhoneNumber());
      this.setEntryDate(usersExt.getEntryDate());
      this.setUpdateDate(usersExt.getUpdateDate());
    }

    return this;
  }

  public UsersExtForm setUserExtToForm(
      UsersExt usersExt) {

    UsersExtForm usersExtForm = new UsersExtForm(usersExtService);

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
}
