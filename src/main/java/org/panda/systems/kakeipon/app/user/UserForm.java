package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.user.RoleService;
import org.panda.systems.kakeipon.domain.service.user.UserService;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "tbl_user")
@SecondaryTable(name = "tbl_role",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "role_id"))
@Data
public class UserForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_user_seq", allocationSize = 1)
  @Column(name = "user_id")
  private Long userId;

  @NotEmpty(message = "ニックネームは必須です")
  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "first_name")
  private String firstName;

  @NotEmpty(message = "パスワードは必須です")
  @Column
  private String password;

  @NotEmpty(message = "メールアドレスは必須です")
  @Column
  private String email;

//  private String yearSelect;
//  private String monthSelect;
//  private String dateSelect;
//  private String birthdayString;
  @Column(name = "birth_day")
  private LocalDateTime birthDay;

  @NotEmpty(message = "電話番号は必須です")
  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "role_id")
  private Long roleId;

  @OneToOne
  @JoinColumn(name = "role_id", table = "tbl_role",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "role_id")
  private RoleForm roleForm;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  // Default constructor
  public UserForm() {
    this.setUserId(Long.parseLong("1"));
  }

  public UserForm(UserService service,
                  RoleService roleService,
                  Long userId,
                  Long roleId) {
    if (userId == null) {
      this.setUserId(Long.parseLong("1"));
    } else {
      this.setUserId(userId);
    }
    User user = service.findById(this.getUserId());

    this.setNickName(user.getNickName());
    this.setLastName(user.getLastName());
    this.setFirstName(user.getFirstName());
    this.setPassword(user.getPassword());
    this.setEmail(user.getEmail());
    this.setBirthDay(user.getBirthDay());
    this.setPhoneNumber(user.getPhoneNumber());
    this.setRoleId(Long.parseLong("2"));
    this.setRoleForm(new RoleForm());
    this.getRoleForm().setRoleId(user.getRoleId());
    this.getRoleForm().setRoleName(
        (new RoleForm(roleService, roleId).getRoleName()));

  }

  public UserForm setUserToForm(Long userId,
                                String nickName,
                                String lastName,
                                String firstName,
                                LocalDateTime birthDay,
                                String password,
                                String phoneNumber,
                                String email,
                                Long roleId,
                                @NotEmpty String roleName,
                                @PastOrPresent LocalDateTime entryDate,
                                LocalDateTime updateDate) {
    UserForm userForm = new UserForm();

    userForm.userId = userId;
    userForm.nickName = nickName;
    userForm.lastName = lastName;
    userForm.firstName = firstName;
    userForm.birthDay = birthDay;
    userForm.password = password;
    userForm.phoneNumber = phoneNumber;
    userForm.email = email;
    userForm.roleId = roleId;
    userForm.roleForm = new RoleForm();
    userForm.getRoleForm().setRoleId(roleId);
    userForm.getRoleForm().setRoleName(roleName);
    userForm.entryDate = entryDate;
    userForm.updateDate = LocalDateTime.now();

    return userForm;
  }
}
