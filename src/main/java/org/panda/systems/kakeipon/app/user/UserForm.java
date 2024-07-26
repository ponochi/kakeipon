package org.panda.systems.kakeipon.app.user;

import jakarta.validation.constraints.NotNull;
import org.panda.systems.kakeipon.domain.model.user.RoleName;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserForm implements Serializable {
  private static final long serialVersionUID = 1L;

  @NotNull( message = "ユーザIDは必須です" )
  private Long userId;

  @NotNull( message = "ニックネームは必須です" )
  private String nickName;

  private String lastName;

  private String firstName;

  @NotNull( message = "パスワードは必須です" )
  private String password;

  @NotNull( message = "メールアドレスは必須です" )
  private String email;

  private String yearSelect;
  private String monthSelect;
  private String dateSelect;
  @NotNull( message = "生年月日は必須です" )
  private String birthdayString;
  private LocalDateTime birthday;

  @NotNull( message = "電話番号は必須です" )
  private String phoneNumber;

  @NotNull( message = "権限は必須です" )
  private RoleName roleName;

  @NotNull( message = "登録日時は必須です" )
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
      ", birthday=" + birthday +
      ", phoneNumber='" + phoneNumber + '\'' +
      ", roleName=" + roleName +
      ", entryDate=" + entryDate +
      ", updateDate=" + updateDate +
      '}';
  }
}
