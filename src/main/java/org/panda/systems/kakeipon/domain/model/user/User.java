package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.Role;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "tbl_user")
@SecondaryTable(name = "tbl_role",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "role_id"))
@Data
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_user_seq", allocationSize = 1)
  @Column(name = "user_id")
  private Long userId;

  //@NotEmpty
  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  //@NotEmpty
  @Column
  private String password;

  //@NotEmpty
  @Column
  private String email;

  @Column(name = "birth_day")
  private LocalDateTime birthDay;

  //@NotEmpty
  @Column(name = "phone_number")
  private String phoneNumber;

  @OneToOne
  @JoinColumn(name = "role_id", table = "tbl_role",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  private Role role;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  // Default constructor
  public User() {
  }
}
