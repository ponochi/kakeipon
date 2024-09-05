package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

  @Query(nativeQuery = true,
      value = "SELECT MAX(ts.specification_id) = " +
      " CASE" +
      "   WHEN MAX(ts.specification_id) IS NULL THEN 1" +
      "   ELSE MAX(ts.specification_id) + 1" +
      " END" +
      " FROM" +
      "   tbl_specification ts" +
      " WHERE" +
      "   ts.specification_group_id = ?1")
  Long getMaxId(Long specificationGroupId);

  @Override
  List<Specification> findAll();

  List<Specification> findBySpecificationGroupId(Long specificationGroupId);

  Specification saveAndFlush(Specification entity);
}
