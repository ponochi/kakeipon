package org.panda.systems.kakeipon.app.user;

import org.hibernate.validator.constraints.NotBlank;
import org.panda.systems.kakeipon.domain.model.user.RoleName;
import org.panda.systems.kakeipon.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class UserInfo implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  // Default constructor
  public UserInfo( ) {
  }

  private Long userId;

  @NotBlank( message = "ニックネームは必須です" )
  private String nickName;

  private String lastName;

  private String firstName;

  @NotBlank( message = "パスワードは必須です" )
  private String password;

  @NotBlank( message = "メールアドレスは必須です" )
  private String email;

  private String yearSelect;
  private String monthSelect;
  private String dateSelect;
  private String birthdayString;
  private LocalDateTime birthday;

  @NotBlank( message = "電話番号は必須です" )
  private String phoneNumber;

  private RoleName roleName;

  private LocalDateTime entryDate;

  private LocalDateTime updateDate;

  public Long getUserId( ) {
    return userId;
  }

  public void setUserId( Long userId ) {
    this.userId = userId;
  }

  public String getNickName( ) {
    return this.nickName;
  }

  public void setNickName( String nickName ) {
    this.nickName = nickName;
  }

  public String getLastName( ) {
    return this.lastName;
  }

  public void setLastName( String lastName ) {
    this.lastName = lastName;
  }

  public String getFirstName( ) {
    return this.firstName;
  }

  public void setFirstName( String firstName ) {
    this.firstName = firstName;
  }

  public String getPassword( ) {
    return this.password;
  }

  public void setPassword( String password ) {
    this.password = password;
  }

  public String getEmail( ) {
    return this.email;
  }

  public void setEmail( String email ) {
    this.email = email;
  }

  public String getYearSelect( ) {
    return this.yearSelect;
  }

  public void setYearSelect( String yearSelect ) {
    this.yearSelect = yearSelect;
  }

  public String getMonthSelect( ) {
    return this.monthSelect;
  }

  public void setMonthSelect( String monthSelect ) {
    this.monthSelect = monthSelect;
  }

  public String getDateSelect( ) {
    return this.dateSelect;
  }

  public void setDateSelect( String dateSelect ) {
    this.dateSelect = dateSelect;
  }

  public String getBirthdayString( ) {
    return this.birthdayString;
  }

  public void setBirthdayString( String birthdayString ) {
    this.birthdayString = birthdayString;
  }

  public LocalDateTime getBirthday( ) {
    return this.birthday;
  }

  public void setBirthday( LocalDateTime birthday ) {
    this.birthday = birthday;
  }

  public String getPhoneNumber( ) {
    return this.phoneNumber;
  }

  public void setPhoneNumber( String phoneNumber ) {
    this.phoneNumber = phoneNumber;
  }

  public RoleName getRoleName( ) {
    if ( this.roleName == null ) {
      return RoleName.USER;
    }
    return this.roleName;
  }

  public void setRoleName( RoleName roleName ) {
    this.roleName = roleName;
  }

  public LocalDateTime getEntryDate( ) {
    return this.entryDate;
  }

  public void setEntryDate( LocalDateTime entryDate ) {
    this.entryDate = entryDate;
  }

  public LocalDateTime getUpdateDate( ) {
    return this.updateDate;
  }

  public void setUpdateDate( LocalDateTime updateDate ) {
    this.updateDate = updateDate;
  }

  @Override
  public String toString( ) {
    return "UserForm{" +
      "userId=" + userId +
      ", nickName='" + nickName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", firstName='" + firstName + '\'' +
      ", password='" + password + '\'' +
      ", birthday=" + birthday + '\'' +
      ", email='" + email + '\'' +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", roleName=" + roleName +
      ", entryDate=" + entryDate +
      ", updateDate=" + updateDate +
      '}';
  }
}
