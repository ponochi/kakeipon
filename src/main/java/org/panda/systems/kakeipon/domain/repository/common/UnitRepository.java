package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

  @SuppressWarnings({"NullableProblems"})
  @Override
  List<Unit> findAll();

  @SuppressWarnings({"NullableProblems"})
  @Override
  Unit getById(Long unitId);

  @Override
  Unit saveAndFlush(Unit unit);
}
