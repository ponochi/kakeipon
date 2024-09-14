package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
          "   ts.user_id = ?3" +
          " AND" +
          "   ts.deleted = ?4")
  Specification findBySpecificationGroupIdAndSpecificationIdAndUserIdAndDeleted(
      Long specificationGroupId, Long specificationId, Long userId, Boolean deleted);

  List<Specification> findAllByDeleted(Boolean deleted);

  List<Specification> findBySpecificationGroupIdAndDeleted(Long specificationGroupId, Boolean deleted);

  Specification findBySpecificationGroupIdAndSpecificationIdAndDeleted(Long specificationGroupId, Long specificationId, Boolean deleted);

  Specification saveAndFlush(Specification entity);

  @Modifying
  @Query(nativeQuery = true,
      value = "UPDATE" +
          "   tbl_specification" +
          " SET" +
          "   deleted = true" +
          " WHERE" +
          "   specification_group_id = ?1")
  void saveAll(Long specificationGroupId);
}
