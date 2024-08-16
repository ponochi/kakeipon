package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.ThirdClassification;
import org.panda.systems.kakeipon.domain.repository.common.ThirdClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class ThirdClassificationService implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Autowired
  private ThirdClassificationRepository thirdClassificationRepository;

  public ThirdClassification getByThirdClassId(Long thirdClassId) {
    return thirdClassificationRepository.getThirdClassificationByThirdClassId(
        thirdClassId);
  }

  public List<ThirdClassification> getBySecondClassId(Long secondClassId) {
    return thirdClassificationRepository.getThirdClassificationsBySecondClassId(
        secondClassId);
  }

  public List<ThirdClassification> findAll() {
    return thirdClassificationRepository.findAll();
  }
}
