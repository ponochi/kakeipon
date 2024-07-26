package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

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

//  @NotBlank
  private LocalDateTime entryDate;

  private LocalDateTime updateDate;

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
}
