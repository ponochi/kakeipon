package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_specification")
@SecondaryTable(name = "tbl_user",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "tbl_currency",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "currency_id"))
@SecondaryTable(name = "tbl_unit",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "unit_id"))
@SecondaryTable(name = "tbl_tax_type",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "tax_type_id"))
@Data
public class Specification implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Column
  private Long specificationGroupId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_seq", allocationSize = 1)
  @Column
  private Long specificationId;

  @Column(name = "user_id")
  private Long userId;

  @Column
  private String name;

  @Column
  private BigDecimal price;

  @Column(name = "currency_id")
  private Long currencyId;

  @Column(name = "unit_id")
  private Long unitId;

  @Column
  private Long quantity;

  @Column(name = "tax_type_id")
  private Long taxTypeId;

  @Column
  private BigDecimal tax;

  @Column
  @Size(max = 1000, message = "メモは1000文字以内で入力してください")
  private String memo;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
