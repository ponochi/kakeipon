package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import org.panda.systems.kakeipon.app.user.UserInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table( name = "usr" )
public class User {
  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @Column( unique = true )
  private Long userId;

  @NotBlank
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

  @Enumerated( EnumType.STRING )
  private RoleName roleName;

  private LocalDateTime entryDate;

  private LocalDateTime updateDate;

  // Default Constructor
  public User( ) {
  }

  // Constructor
  public User( UserInfo info ) {
    this.userId = info.getUserId();
    this.nickName = info.getNickName();
    this.lastName = info.getLastName();
    this.firstName = info.getFirstName();
    this.password = info.getPassword();
    this.email = info.getEmail();
    this.birthday = LocalDateTime.parse(
            info.getBirthdayString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME );
    this.phoneNumber = info.getPhoneNumber();
    this.roleName = info.getRoleName();
    if ( info.getEntryDate() == null ) {
      this.entryDate = LocalDateTime.now();
    } else {
      this.entryDate = info.getEntryDate();
    }
    this.updateDate = info.getUpdateDate();
  }

  public Long getUserId( ) {
    return this.userId;
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

  public String getFirstName( ) {
    return this.firstName;
  }

  public void setFirstName( String firstName ) {
    this.firstName = firstName;
  }

  public String getLastName( ) {
    return this.lastName;
  }

  public void setLastName( String lastName ) {
    this.lastName = lastName;
  }

  // ToDo: implements decryption
  public String getPassword( ) {
    return password;
  }

  // ToDo: implements encryption
  public void setPassword( String password ) {
    this.password = password;
  }

  public String getEmail( ) {
    return this.email;
  }

  public void setEmail( String email ) {
    this.email = email;
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
    return "User{" +
            "userId=" + this.userId +
            ", nickName='" + this.nickName + '\'' +
            ", lastName='" + this.lastName + '\'' +
            ", firstName='" + this.firstName + '\'' +
            ", password='" + this.password + '\'' +
            ", birthday=" + this.birthday + '\'' +
            ", email='" + this.email + '\'' +
            ", phoneNumber='" + this.phoneNumber + '\'' +
            ", roleName=" + this.roleName +
            ", entryDate=" + this.entryDate +
            ", updateDate=" + this.updateDate +
            '}';
  }
}
