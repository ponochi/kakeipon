package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.BalanceType;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_specification_group")
@SecondaryTable(name = "tbl_user",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "tbl_balance_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "balance_type_id"))
@SecondaryTable(name = "tbl_account_and_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_and_balance_id"))
@Data
public class SpecificationGroup implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  private Long specificationGroupId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "shop_id")
  private Long shopId;

  @PastOrPresent
  @Column
  private LocalDate receivingAndPaymentDate;

  @Column
  private LocalTime receivingAndPaymentTime;

  @Column(name = "balance_type_id")
  private Long balanceTypeId;

  @Column(name = "account_and_balance_id")
  private Long accountAndBalanceId;

  @Column
  private String memo;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
