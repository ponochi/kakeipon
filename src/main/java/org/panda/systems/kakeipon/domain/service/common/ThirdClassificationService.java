package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.ThirdClassification;
import org.panda.systems.kakeipon.domain.repository.common.ThirdClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ThirdClassificationService implements Serializable {

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

}
