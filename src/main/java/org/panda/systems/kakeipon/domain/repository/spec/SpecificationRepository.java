package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpecificationRepository
        extends JpaRepository<Specification, Long> {

    @Query(nativeQuery = true,
            value = "SELECT" +
                    " MAX(ts.specification_id) + 1" +
                    " FROM" +
                    "   specification ts" +
                    " WHERE" +
                    "   ts.specification_group_id = ?1")
    Long getNextId(Long specificationGroupId);

    @Query(nativeQuery = true,
            value = "SELECT" +
                    " ts.specification_group_id," +
                    " ts.specification_id," +
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
                    "   ts.user_id = ?3" +
                    " AND" +
                    "   ts.deleted = ?4")
    Specification findBySpecificationGroupIdAndSpecificationIdAndUserIdAndDeleted(
        Long specificationGroupId, Long specificationId, Long userId, Boolean deleted);

    List<Specification> findAllByDeleted(Boolean deleted);

  List<Specification> findBySpecificationGroupIdAndUserIdAndDeletedOrderBySpecificationIdDesc(
            Long specificationGroupId, Long userId, Boolean deleted);

    @SuppressWarnings({"unchecked", "NullableProblems"})
    Specification saveAndFlush(Specification entity);

//    @Modifying
    @Query(nativeQuery = true,
            value =
                    "UPDATE" +
                            "   specification" +
                            " SET" +
                            "   user_id = :userId," +
                            "   name = :name," +
                            "   price = :price," +
                            "   currency_id = :currencyId," +
                            "   quantity = :quantity," +
                            "   unit_id = :unitId," +
                            "   tax_type_id = :taxTypeId," +
                            "   tax_rate_id = :taxRateId," +
                            "   tax = :tax," +
                            "   spec_memo = :specMemo," +
                            "   deleted = :deleted," +
                            "   entry_date = :entryDate," +
                            "   update_date = :updateDate" +
                            " WHERE " +
                            "   specification_group_id = :specificationGroupId" +
                            " AND " +
                            "   specification_id = :specificationId")
    void update(
            @Param("specificationGroupId") Long specificationGroupId,
            @Param("specificationId") Long specificationId,
            @Param("userId") Long userId,
            @Param("name") String name,
            @Param("price") Long price,
            @Param("currencyId") Long currencyId,
            @Param("quantity") Long quantity,
            @Param("unitId") Long unitId,
            @Param("taxTypeId") Long taxTypeId,
            @Param("taxRateId") Long taxRateId,
            @Param("tax") Long tax,
            @Param("specMemo") String specMemo,
            @Param("deleted") Boolean deleted,
            @Param("entryDate") LocalDateTime entryDate,
            @Param("updateDate") LocalDateTime updateDate);

//    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE" +
                    "   specification" +
                    " SET" +
                    "   deleted = true" +
                    " WHERE" +
                    "   specification_group_id = :specificationGroupId")
    void delete(
            @Param("specificationGroupId") Long specificationGroupId);

}
