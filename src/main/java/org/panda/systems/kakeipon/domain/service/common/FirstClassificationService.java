package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.FirstClassification;
import org.panda.systems.kakeipon.domain.repository.common.FirstClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class FirstClassificationService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private FirstClassificationRepository firstClassificationRepository;

  public FirstClassification getById(Long firstClassId) {
    return firstClassificationRepository.getById(firstClassId);
  }

  public List<FirstClassification> findAll() {
    return firstClassificationRepository.findAll();
  }
}
