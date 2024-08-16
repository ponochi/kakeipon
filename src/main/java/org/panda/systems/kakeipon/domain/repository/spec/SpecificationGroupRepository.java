package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecificationGroupRepository extends JpaRepository<SpecificationGroup, Long> {
  @Override
  List<SpecificationGroup> findAll();

  @SuppressWarnings("NullableProblems")
  @Override
  Optional<SpecificationGroup> findById(Long specificationGroupId);

  SpecificationGroup saveAndFlush(SpecificationGroup entity);
}
