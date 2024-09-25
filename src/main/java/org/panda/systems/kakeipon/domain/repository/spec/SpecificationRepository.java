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
          "   specification ts" +
          " WHERE" +
          "   ts.specification_group_id = ?1")
  Long getMaxId(Long specificationGroupId);

  @Query(nativeQuery = true,
      value = "SELECT" +
          " ts.specification_id," +
          " ts.specification_group_id," +
          " ts.id," +
          " ts.name," +
          " ts.price," +
          " ts.currency_id," +
          " ts.quantity," +
          " ts.unit_id," +
          " ts.tax_type_id," +
          " ts.tax_rate_id," +
          " ts.tax," +
          " ts.spec_memo," +
          " ts.deleted," +
          " ts.entry_date," +
          " ts.update_date," +
          " ts.version" +
          " FROM" +
          "   specification ts" +
          " WHERE" +
          "   ts.specification_group_id = ?1" +
          " AND" +
          "   ts.specification_id = ?2" +
          " AND" +
          "   ts.id = ?3" +
          " AND" +
          "   ts.deleted = ?4")
  Specification findBySpecificationGroupIdAndSpecificationIdAndIdAndDeleted(
      Long specificationGroupId, Long specificationId, Integer id, Boolean deleted);

  List<Specification> findAllByDeleted(Boolean deleted);

  List<Specification> findBySpecificationGroupIdAndIdAndDeleted(
      Long specificationGroupId, Integer id, Boolean deleted);

  Specification saveAndFlush(Specification entity);

  @Modifying
  @Query(nativeQuery = true,
      value = "UPDATE" +
          "   specification" +
          " SET" +
          "   deleted = true" +
          " WHERE" +
          "   specification_group_id = ?1")
  void saveAll(Long specificationGroupId);
}
