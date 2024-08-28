package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.Balance;
import org.panda.systems.kakeipon.domain.model.common.Shop;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tbl_specification_group")
@SecondaryTable(name = "tbl_user",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "tbl_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "balance_id"))
@SecondaryTable(name = "tbl_account_and_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_and_balance_id"))
@Data
public class SpecificationGroup implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_group_seq", allocationSize = 1)
  private Long specificationGroupId;

//  @Column(name = "user_id")
//  private Long userId;

  @ManyToOne
  @JoinColumn(name = "user_id")
//  @MapsId("userId")
  private User user;

  @Column(name = "shop_id")
  private Long shopId;

  @ManyToOne
  @JoinColumn(name = "shop_id", referencedColumnName = "shop_id",
      insertable = false, updatable = false)
  private Shop shop;

  @PastOrPresent
  @Column
  private LocalDate receivingAndPaymentDate;

  @Column
  private LocalTime receivingAndPaymentTime;

  @Column(name = "balance_id")
  private Long balanceId;

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "balance_id", table = "tbl_balance",
          referencedColumnName = "balance_id",
          insertable = false, updatable = false),
  })
  private Balance balance;

  @Column(name = "account_and_balance_id")
  private Long accountAndBalanceId;

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "account_and_balance_id",
          table = "tbl_account_and_balance",
          referencedColumnName = "account_and_balance_id",
          insertable = false, updatable = false),
  })
  private AccountAndBalance accountAndBalance;

  @Column
  private String memo;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
