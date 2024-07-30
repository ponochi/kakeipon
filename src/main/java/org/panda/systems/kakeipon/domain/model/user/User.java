package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import org.panda.systems.kakeipon.domain.model.common.Role;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table( name = "tbl_user" )
public class User implements Serializable {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_user_seq", allocationSize = 1)
  private Long userId;

  @NotBlank
  @Column
  private String nickName;

  private String firstName;

  private String lastName;

  @NotBlank
  private String password;

  @NotBlank
  private String email;

  private LocalDateTime birthday;

  @NotBlank
  private String phoneNumber;

  private Long roleId;

  @OneToOne
  @JoinColumn( name = "roleId", referencedColumnName = "roleId",
      insertable = false, updatable = false )
  private Role role;

  private LocalDateTime entryDate;

  private LocalDateTime updateDate;

  // Default Constructor
  public User() {

  }

  // Constructor
  public User( User user ) {
    this.userId = user.getUserId();
    this.nickName = user.getNickName();
    this.lastName = user.getLastName();
    this.firstName = user.getFirstName();
    this.password = user.getPassword();
    this.email = user.getEmail();
    if ( user.getBirthday() == null ) {
      this.birthday = LocalDateTime.parse("1970-01-01T00:00");
    } else {
      this.birthday = user.getBirthday();
    }
    this.phoneNumber = user.getPhoneNumber();
    this.roleId = user.getRoleId();
    if ( user.getEntryDate() == null ) {
      this.entryDate = LocalDateTime.now();
    } else {
      this.entryDate = user.getEntryDate();
    }
    this.updateDate = user.getUpdateDate();
  }

  public Long getUserId( ) {
    return this.userId;
  }

  public void setUserId(Long userId ) {
    this.userId = userId;
  }

  public String getNickName( ) {
    return this.nickName;
  }

  public void setNickName(String nickName ) {
    this.nickName = nickName;
  }

  public String getFirstName( ) {
    return this.firstName;
  }

  public void setFirstName(String firstName ) {
    this.firstName = firstName;
  }

  public String getLastName( ) {
    return this.lastName;
  }

  public void setLastName(String lastName ) {
    this.lastName = lastName;
  }

  // ToDo: implements decryption
  public String getPassword( ) {
    return password;
  }

  // ToDo: implements encryption
  public void setPassword(String password ) {
    this.password = password;
  }

  public String getEmail( ) {
    return this.email;
  }

  public void setEmail(String email ) {
    this.email = email;
  }

  public LocalDateTime getBirthday( ) {
    return this.birthday;
  }

  public void setBirthday(LocalDateTime birthday ) {
    this.birthday = birthday;
  }

  public String getPhoneNumber( ) {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber ) {
    this.phoneNumber = phoneNumber;
  }

  public Long getRoleId( ) { return this.roleId; }

  public void setRoleId(Long roleId ) { this.roleId = roleId; }

  public Role getRole( )
  {
    return this.role;
  }

  public void setRole( Role role ) {
    this.role = role;
  }

  public String getRoleName( ) {
    if ( this.role == null ) {
      Role role = new Role();
      role.setRoleId( this.roleId );
      return role.getRoleName();
    } else {
      return this.getRole().getRoleName();
    }
  }

  public LocalDateTime getEntryDate( ) {
    return this.entryDate;
  }

  public void setEntryDate(LocalDateTime entryDate ) {
    this.entryDate = entryDate;
  }

  public LocalDateTime getUpdateDate( ) {
    return this.updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate ) {
    this.updateDate = updateDate;
  }

  @Override
  public String toString( ) {
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
            ", role_id=" + this.roleId +
            strRole +
//             ", role.role_name" + this.getRole().getRoleName() +
            ", entry_date=" + this.entryDate +
            ", update_date=" + this.updateDate +
            '}';
  }
}
