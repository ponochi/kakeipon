package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

// Income and Expenditure
//
@Entity
@Table(name = "tbl_second_class")
@IdClass(SecondClassificationEntityPkey.class)
@Data
public class SecondClassification implements Serializable {

  @Column(name = "first_class_id")
  private Long firstClassId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_second_class_seq", allocationSize = 1)
  private Long secondClassId;

  private String secondClassName;
}
