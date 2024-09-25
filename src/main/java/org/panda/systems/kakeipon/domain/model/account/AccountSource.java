package org.panda.systems.kakeipon.domain.model.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_info")
@Data
public class AccountSource implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "account_info_seq", allocationSize = 1)
  @Column(name = "account_id")
  private Long accountSourceId;

  @NotEmpty
  @Column(name = "account_name")
  private String accountName;

  @Column(name = "branch_name")
  private String branchName;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
