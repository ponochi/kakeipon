package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

// Income and Expenditure
//
@Entity
@Table(name = "second_class")
@IdClass(SecondClassificationEntityPkey.class)
@Data
public class SecondClassification implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Column(name = "first_class_id")
  private Long firstClassId;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "second_class_seq", allocationSize = 1)
  @Column(name = "second_class_id")
  private Long secondClassId;

  private String secondClassName;
}
