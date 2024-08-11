package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.SecondClassification;
import org.panda.systems.kakeipon.domain.repository.common.SecondClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class SecondClassificationService implements Serializable {

  @Autowired
  SecondClassificationRepository secondClassificationRepository;

  public SecondClassification getSecondClassificationBySecondClassId(Long secondClassId) {
    return secondClassificationRepository.getSecondClassificationBySecondClassId(secondClassId);
  }

  public List<SecondClassification> getSecondClassificationsByFirstClassId(Long firstClassId) {
    return secondClassificationRepository.getSecondClassificationsByFirstClassId(firstClassId);
  }

  public List<SecondClassification> findAll() {
    return secondClassificationRepository.findAll();
  }
}
