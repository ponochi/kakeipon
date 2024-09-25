package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.UserExt;
import org.panda.systems.kakeipon.domain.service.user.UserExtService;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "users")
@Data
public class UserExtForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Integer id;

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
  public UserExtForm() {
//    this.setId(Integer.parseInt("1"));
  }

  public UserExtForm(UserExtService service,
                     Integer id) {
    if (id == null) {
//      this.setId(Integer.parseInt("1"));
    } else {
      this.setId(id);
    }
    UserExt userExt = service.findById(this.getId());

    this.setLastName(userExt.getLastName());
    this.setFirstName(userExt.getFirstName());
    this.setEmail(userExt.getEmail());
    this.setBirthDay(userExt.getBirthDay());
    this.setPhoneNumber(userExt.getPhoneNumber());
    this.setEntryDate(userExt.getEntryDate());
    this.setUpdateDate(userExt.getUpdateDate());
  }

  public UserExtForm setUserExtToForm(
      UserExt userExt) {

    UserExtForm userExtForm = new UserExtForm();

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
}
