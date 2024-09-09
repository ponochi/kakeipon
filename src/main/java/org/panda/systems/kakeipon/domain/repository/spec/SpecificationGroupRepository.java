package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecificationGroupRepository extends JpaRepository<SpecificationGroup, Long> {
  @Query(nativeQuery = true,
      value = "SELECT" +
          "  MAX(specification_group_id)" +
          "FROM" +
          "  tbl_specification_group")
  Long getMaxGroupId();

  @Override
  List<SpecificationGroup> findAll();

  @SuppressWarnings("NullableProblems")
  @Override
  Optional<SpecificationGroup> findById(Long specificationGroupId);

  @SuppressWarnings({"NullableProblems", "unchecked"})
  SpecificationGroup saveAndFlush(SpecificationGroup specificationGroup);
}
