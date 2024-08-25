package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_third_class" )
@IdClass(ThirdClassificationEntityPkey.class)
@Data
public class ThirdClassification {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_third_class_seq", allocationSize = 1)
  private Long thirdClassId;

  @Id
  private Long secondClassId;

  @Id
  private Long firstClassId;

  private String thirdClassName;
}
