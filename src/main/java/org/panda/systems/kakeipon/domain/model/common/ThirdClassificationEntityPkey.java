package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ThirdClassificationEntityPkey
    implements Serializable {

  @Column
  private Long thirdClassId;

  @Column
  private Long secondClassId;

  @Column
  private Long firstClassId;
}
