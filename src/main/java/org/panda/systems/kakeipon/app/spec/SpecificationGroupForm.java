package org.panda.systems.kakeipon.app.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SpecificationGroupForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  Long specificationGroupId;

  @OneToOne
  @JoinColumn(name = "user_id", table = "tbl_user",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  User user;

  @OneToOne
  @JoinColumn(name = "shop_id", table = "tbl_shop",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  Shop shop;

  @PastOrPresent
  @Column
  LocalDate receivingAndPaymentDate;

  @Column
  LocalTime receivingAndPaymentTime;

  @PositiveOrZero
  @Column
  Long receivingAndPaymentType;

  @OneToOne
  @JoinColumn(name = "account_source_id", table = "tbl_account",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  AccountAndBalance accountSource;

  @OneToOne
  @JoinColumn(name = "account_destination_id", table = "tbl_account",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column
  AccountAndBalance accountDestination;

  @Column
  String memo;

  @PastOrPresent
  @Column
  LocalDateTime entry_date;

  @Column
  LocalDateTime update_date;

  // Default constructor
  public SpecificationGroupForm() {
  }
}
