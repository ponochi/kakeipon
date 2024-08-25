package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountSourceForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tblAccountSeq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "account_id")
  private Long accountId;

  @NotEmpty
  @Column
  private String accountName;

  @NotEmpty
  @Column
  private String branchName;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
