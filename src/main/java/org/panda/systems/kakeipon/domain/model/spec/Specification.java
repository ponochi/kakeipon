package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "specification")
@SecondaryTable(name = "specification_group",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "specification_group_id"))
@SecondaryTable(name = "users",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
@SecondaryTable(name = "currency",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "currency_id"))
@SecondaryTable(name = "unit",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "unit_id"))
@SecondaryTable(name = "tax_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "tax_type_id"))
@SecondaryTable(name = "tax_rate",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "tax_rate_id"))
@Data
public class Specification implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Column(name = "specification_group_id")
  private Long specificationGroupId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "specification_seq", allocationSize = 1)
  @Column(name = "specification_id")
  private Long specificationId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private Long price;

  @Column(name = "currency_id")
  private Long currencyId;

  @Column(name = "unit_id")
  private Long unitId;

  @Column(name = "quantity")
  private Long quantity;

  @Column(name = "tax_type_id")
  private Long taxTypeId;

  @Column(name = "tax_rate_id")
  private Long taxRateId;

  @Column(name = "tax")
  private Long tax;

  @Column(name = "spec_memo")
  @Size(max = 1000, message = "メモは1000文字以内で入力してください")
  private String specMemo;

  @Column(name = "deleted")
  private Boolean deleted;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  @Version
  @Column(name = "version")
  private Long version;
}
