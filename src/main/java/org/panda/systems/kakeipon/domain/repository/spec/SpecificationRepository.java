package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {

  @Override
  public List<Specification> findAll();

  public Optional<Specification> findById(Long specificationGroupId);

  <S extends Specification> List<S> saveAndFlush(List<S> entities);
}
