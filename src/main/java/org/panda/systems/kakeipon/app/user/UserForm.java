package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import org.panda.systems.kakeipon.domain.model.common.Role;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class UserForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_user_seq", allocationSize = 1)
  @Column(name = "user_id")
  private Long userId;

  @NotEmpty(message = "ニックネームは必須です")
  @Column
  private String nickName;

  @Column
  private String lastName;

  @Column
  private String firstName;

  @SuppressWarnings("deprecation")
  @NotEmpty(message = "パスワードは必須です")
  @Column
  private String password;

  @SuppressWarnings("deprecation")
  @NotEmpty(message = "メールアドレスは必須です")
  @Column
  private String email;

  private String yearSelect;
  private String monthSelect;
  private String dateSelect;
  private String birthdayString;
  @Column
  private LocalDateTime birthday;

  @NotEmpty(message = "電話番号は必須です")
  @Column
  private String phoneNumber;

  @Column
  private Long roleId;

  @OneToOne
  @JoinColumn(name = "role_id", table = "tbl_role",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  private Role role;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default constructor
  public UserForm() {
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getNickName() {
    return this.nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getYearSelect() {
    return this.yearSelect;
  }

  public void setYearSelect(String yearSelect) {
    this.yearSelect = yearSelect;
  }

  public String getMonthSelect() {
    return this.monthSelect;
  }

  public void setMonthSelect(String monthSelect) {
    this.monthSelect = monthSelect;
  }

  public String getDateSelect() {
    return this.dateSelect;
  }

  public void setDateSelect(String dateSelect) {
    this.dateSelect = dateSelect;
  }

  public String getBirthdayString() {
    return this.birthdayString;
  }

  public void setBirthdayString(String birthdayString) {
    this.birthdayString = birthdayString;
  }

  public LocalDateTime getBirthday() {
    return this.birthday;
  }

  public void setBirthday(LocalDateTime birthday) {
    this.birthday = birthday;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Long getRoleId() { return this.roleId; }

  public void setRoleId(Long roleId) { this.roleId = roleId; }

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) { this.role = role; }

  public LocalDateTime getEntryDate() {
    return this.entryDate;
  }

  public void setEntryDate(LocalDateTime entryDate) {
    this.entryDate = entryDate;
  }

  public LocalDateTime getUpdateDate() {
    return this.updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }

  @Override
  public String toString() {
    return "UserForm {" +
        "user_id=" + userId +
        ", nick_name='" + nickName + '\'' +
        ", last_name='" + lastName + '\'' +
        ", first_name='" + firstName + '\'' +
        ", password='" + password + '\'' +
        ", birthday=" + birthday + '\'' +
        ", email='" + email + '\'' +
        ", phone_number='" + phoneNumber + '\'' +
        ", role_id='" + roleId + '\'' +
        ", entry_date=" + entryDate +
        ", update_date=" + updateDate + '}';
  }
}
