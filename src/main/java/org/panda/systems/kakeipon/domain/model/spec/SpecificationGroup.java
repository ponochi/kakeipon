package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "specification_group")
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@SecondaryTable(name = "balance_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "balance_type_id"))
@SecondaryTable(name = "account_and_balance",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "account_and_balance_id"))
@Data
public class SpecificationGroup implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "specification_group_seq", allocationSize = 1)
  @Column(name = "specification_group_id")
  private Long specificationGroupId;

  @Column(name = "id")
  private Integer id;

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

  @Column(name = "group_memo")
  private String groupMemo;

  @Column(name = "deleted")
  private Boolean deleted;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  @Version
  @Column(name = "version")
  private Long version;
}
