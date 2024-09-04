package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_first_class" )
@Data
public class FirstClassification implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_first_class_seq", allocationSize = 1)
  @Column(name = "first_class_id")
  private Long firstClassId;

  @Column
  private String firstClassName;
}
