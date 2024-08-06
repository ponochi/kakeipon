package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.FirstClassification;
import org.panda.systems.kakeipon.domain.repository.common.FirstClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class FirstClassificationService implements Serializable {

  @Autowired
  private FirstClassificationRepository firstClassificationRepository;

  public FirstClassificationService() {
    super();
  }

  FirstClassification getById(Long firstClassId) {
    return firstClassificationRepository.getById(firstClassId);
  }
}
