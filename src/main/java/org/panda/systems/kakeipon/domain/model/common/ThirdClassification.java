package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_third_class" )
@IdClass(ThirdClassificationEntityPkey.class)
@Data
public class ThirdClassification implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_third_class_seq", allocationSize = 1)
  @Column(name = "third_class_id")
  private Long thirdClassId;

  @Id
  private Long secondClassId;

  @Id
  private Long firstClassId;

  private String thirdClassName;
}
