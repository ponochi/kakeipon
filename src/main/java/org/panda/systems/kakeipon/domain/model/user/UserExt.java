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
@Table(name = "users_ext")
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@Data
public class UserExt implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column
  private String email;

  @Column(name = "birth_day")
  private LocalDateTime birthDay;

  @Column(name = "phone_number")
  private String phoneNumber;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;
}
