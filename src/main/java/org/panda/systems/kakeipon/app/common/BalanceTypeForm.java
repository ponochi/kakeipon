package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.BalanceType;
import org.panda.systems.kakeipon.domain.service.common.BalanceTypeService;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Table(name = "balance_type")
@Data
public class BalanceTypeForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final BalanceTypeService balanceTypeService;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "balance_type_seq", allocationSize = 1)
  @Column(name = "balance_type_id")
  private Long balanceTypeId;

  @NotEmpty
  @Column(name = "balance_type_name")
  private String balanceTypeName;

  // Default constructor
  public BalanceTypeForm() {

    this.balanceTypeService = null;
  }

  public BalanceTypeForm(BalanceTypeService balanceTypeService) {

    this.balanceTypeService = balanceTypeService;
    this.setBalanceTypeId(Long.parseLong("1"));
  }

  public BalanceTypeForm serBalanceTypeFormByDB(Long balanceTypeId) {

    if (balanceTypeId == null) {
      this.setBalanceTypeId(Long.parseLong("1"));
    } else {
      this.setBalanceTypeId(balanceTypeId);
    }
    BalanceType balanceType
        = balanceTypeService.findById(this.getBalanceTypeId());
    this.setBalanceTypeName(balanceType.getBalanceTypeName());

    return this;
  }
}
