package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.common.FirstClassification;
import org.panda.systems.kakeipon.domain.model.common.SecondClassification;
import org.panda.systems.kakeipon.domain.model.common.ThirdClassification;

import java.io.Serializable;
import java.util.List;

@Data
public class ClassificationsForm implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_first_class_seq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "first_class_id")
  private Long firstClassId;
  @Column
  private String firstClassName;
  @Column
  private FirstClassification firstClassification;
  @Column
  private List<FirstClassification> firstClassifications;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_second_class_seq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "second_class_id")
  private Long secondClassId;
  @Column
  private String secondClassName;
  @Column
  private SecondClassification secondClassification;
  @Column
  private List<SecondClassification> secondClassifications;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_third_class_seq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "third_class_id")
  private Long thirdClassId;
  @Column
  private String thirdClassName;
  @Column
  private ThirdClassification thirdClassification;
  @Column
  private List<ThirdClassification> thirdClassifications;
}
