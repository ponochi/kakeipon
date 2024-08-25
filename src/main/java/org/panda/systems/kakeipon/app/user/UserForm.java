package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.Role;

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

  // @Column(name = "role_id")
  // private Long roleId;

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
}
