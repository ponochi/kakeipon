package org.panda.systems.kakeipon.domain.model.spec;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_specification")
@Data
public class Specification implements Serializable {
  @NotEmpty
  @Column
  private Long specificationGroupId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_specification_seq", allocationSize = 1)
  @Column
  private Long specificationId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id",
      insertable = false, updatable = false)
  private User user;

  @Column
  private String itemName;

  @Column
  private BigDecimal itemsJpyPrice;

  @Column
  @Size(max = 3, min = 3, message = "通貨の種類は3文字で入力してください (ex. JPY, USD, ...)")
  private String currencyName;

  @Column
  private BigDecimal itemsPrice;

  @Column
  private Integer itemCount;

  @Column
  @Size(max = 1000, message = "メモは1000文字以内で入力してください")
  private String memo;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
