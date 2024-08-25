package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id",
      insertable = false, updatable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "shop_id", referencedColumnName = "shop_id",
      insertable = false, updatable = false)
  private Shop shop;

  @PastOrPresent
  @Column
  private LocalDate receivingAndPaymentDate;

  @NotEmpty
  @Column
  private LocalTime receivingAndPaymentTime;

  @OneToOne
  @JoinColumns({
      @JoinColumn(name = "balance_id", table = "tbl_balance",
          referencedColumnName = "balance_id",
          insertable = false, updatable = false),
  })
  private Balance balance;

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
