package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_account_and_balance")
@Data
public class AccountAndBalance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_account_and_balance_seq", allocationSize = 1)
  @Column(name = "account_and_balance_id")
  private Long accountAndBalanceId;

  @Column(name = "account_source_id")
  private Long accountSourceId;

  @Column(name = "account_destination_id")
  private Long accountDestinationId;

  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
