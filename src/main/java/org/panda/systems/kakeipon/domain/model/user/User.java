package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Component
@Entity
@Table(name = "tbl_user")
@SecondaryTable(name = "tbl_role",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "role_id"))
@Data
public class User implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_user_seq", allocationSize = 1)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "nick_name")
  private String nickName;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column
  private String password;

  @Column
  private String email;

  @Column(name = "birth_day")
  private LocalDateTime birthDay;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "role_id")
  private Long roleId;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;
}
