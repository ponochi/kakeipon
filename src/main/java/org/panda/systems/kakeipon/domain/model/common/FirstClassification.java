package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_first_class" )
public class FirstClassification {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_first_class_seq", allocationSize = 1)
  private Long firstClassId;

  private String firstClassName;
}
