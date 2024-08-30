package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.AccountDestination;
import org.panda.systems.kakeipon.domain.model.common.AccountSource;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "tbl_account_info")
@Data
public class AccountSourceForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_account_info_seq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "account_id")
  private Long accountSourceId;

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
