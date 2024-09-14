package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.Unit;
import org.panda.systems.kakeipon.domain.service.common.UnitService;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "tbl_unit")
@Data
public class UnitForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_unit_seq", allocationSize = 1)
  @Column(name = "unit_id")
  private Long unitId;

  @Column(name = "unit_name")
  private String unitName;

  // Default constructor
  public UnitForm() {

  }

  public UnitForm(UnitService service,
                  Long unitId) {
    Unit unit = service.findById(unitId);

    this.unitId = unit.getUnitId();
    this.unitName = unit.getUnitName();
  }
}
