package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.app.common.AccountAndBalanceForm;
import org.panda.systems.kakeipon.app.common.ShopForm;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "tbl_specification_group")
@SecondaryTable(name = "tbl_user",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "tbl_shop",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "shop_id"))
@SecondaryTable(name = "tbl_account_info",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_source_id"))
@SecondaryTable(name = "tbl_account_info",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_destination_id"))
@Data
public class SpecificationGroupForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  Long specificationGroupId;

  @Column(name = "user_id")
  Long userId;

  @ManyToOne
  @JoinColumn(name = "user_id", table = "tbl_user",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  User user;

  @Column(name = "shop_id")
  Long shopId;

  @OneToOne
  @JoinColumn(name = "shop_id", table = "tbl_shop",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "shop_id")
  ShopForm shopForm;

  @PastOrPresent
  @Column
  LocalDate receivingAndPaymentDate;

  @Column
  LocalTime receivingAndPaymentTime;

  @PositiveOrZero
  @Column
  Long receivingAndPaymentType;

  @Column(name = "balance_id")
  Long balanceId;

  @OneToOne
  @JoinColumn(name = "account_and_balance_id", table = "tbl_account_and_balance",
      insertable = false, updatable = false)
  @Column(name = "account_and_balance_form")
  AccountAndBalanceForm accountAndBalanceForm;

  @Column
  String memo;

  @PastOrPresent
  @Column
  LocalDateTime entry_date;

  @Column
  LocalDateTime update_date;
}
