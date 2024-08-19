package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import org.panda.systems.kakeipon.domain.model.common.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_user_seq", allocationSize = 1)
  @Column(name = "user_id")
  private Long userId;

  @NotEmpty
  @Column
  private String nickName;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @NotEmpty
  @Column
  private String password;

  @NotEmpty
  @Column
  private String email;

  @Column
  private LocalDateTime birthday;

  @NotEmpty
  @Column
  private String phoneNumber;

  @NotEmpty
  @OneToOne
  @JoinColumn(name = "role_id", referencedColumnName = "role_id",
      insertable = false, updatable = false)
  private Role role;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default Constructor
  public User() {

  }

  public Long getUserId() {
    return this.userId;
  }

  public void setUserId(Long user_id) {
    this.userId = user_id;
  }

  public String getNickName() {
    return this.nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  // ToDo: implements decryption
  public String getPassword() {
    return password;
  }

  // ToDo: implements encryption
  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getRoleName() {
    if (this.role == null) {
      Role role = new Role();
      return role.getRoleName();
    } else {
      return this.getRole().getRoleName();
    }
  }

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
    String strRole;
    if (this.role == null) {
      strRole = ", role=null";
    } else {
      strRole = ", role=" + this.role.getRoleName();
    }
    return "User {" +
        "user_id=" + this.userId +
        ", nick_name='" + this.nickName + '\'' +
        ", last_name='" + this.lastName + '\'' +
        ", first_name='" + this.firstName + '\'' +
        ", password='" + this.password + '\'' +
        ", birthday=" + this.birthday + '\'' +
        ", email='" + this.email + '\'' +
        ", phone_number='" + this.phoneNumber + '\'' +
        ", role_id=" + this.getRole().getRoleId() +
        ", role_name" + this.getRole().getRoleName() +
        ", entry_date=" + this.entryDate +
        ", update_date=" + this.updateDate +
        '}';
  }
}
