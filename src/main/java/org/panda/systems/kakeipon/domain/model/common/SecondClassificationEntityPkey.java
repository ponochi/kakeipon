package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SecondClassificationEntityPkey
    implements Serializable {

  @Column
  private Long secondClassId;
}
