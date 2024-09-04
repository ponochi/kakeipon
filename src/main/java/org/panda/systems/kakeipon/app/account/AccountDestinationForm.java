package org.panda.systems.kakeipon.app.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountDestinationForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tblAccountSeq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "account_id")
  private Long accountDestinationId;

  @NotEmpty
  @Column(name = "account_name")
  private String accountName;

  @Column(name = "branch_name")
  private String branchName;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;
}
