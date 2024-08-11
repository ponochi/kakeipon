package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.FirstClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirstClassificationRepository
    extends JpaRepository<FirstClassification, Long> {

  @Override
  FirstClassification getById(Long firstClassId);

  @Override
  List<FirstClassification> findAll();
}
