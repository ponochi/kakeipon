package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "unit")
@Data
public class Unit implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "unit_seq", allocationSize = 1)
  @Column(name = "unit_id")
  private Long unitId;

  private String unitName;
}
