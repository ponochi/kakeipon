package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

  @Query(nativeQuery = true,
      value = "SELECT" +
          " MAX(ts.specification_id) + 1" +
          " FROM" +
          "   tbl_specification ts" +
          " WHERE" +
          "   ts.specification_group_id = ?1")
  Long getMaxId(Long specificationGroupId);

  @Query(nativeQuery = true,
      value = "SELECT" +
          " ts.specification_id," +
          " ts.specification_group_id," +
          " ts.user_id," +
          " ts.name," +
          " ts.price," +
          " ts.currency_id," +
          " ts.quantity," +
          " ts.unit_id," +
          " ts.tax_type_id," +
          " ts.tax_rate_id," +
          " ts.tax," +
          " ts.spec_memo," +
          " ts.entry_date," +
          " ts.update_date" +
          " FROM" +
          "   tbl_specification ts" +
          " WHERE" +
          "   ts.specification_group_id = ?1" +
          " AND" +
          "   ts.specification_id = ?2" +
          " AND" +
          "   ts.user_id = ?3")
  Specification findBySpecificationGroupIdAndSpecificationIdAndUserId(
      Long specificationGroupId, Long specificationId, Long userId);

  @Override
  List<Specification> findAll();

  List<Specification> findBySpecificationGroupId(Long specificationGroupId);

  Specification findBySpecificationGroupIdAndSpecificationId(Long specificationGroupId, Long specificationId);

  Specification saveAndFlush(Specification entity);
}
