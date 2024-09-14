package org.panda.systems.kakeipon.domain.repository.spec;

import org.panda.systems.kakeipon.domain.model.spec.SpecificationGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

  List<SpecificationGroup> findAllByDeleted(Boolean deleted, Sort sort);

  @SuppressWarnings("NullableProblems")
  Optional<SpecificationGroup> findBySpecificationGroupIdAndDeleted(
      Long specificationGroupId, Boolean deleted);

  @SuppressWarnings({"NullableProblems", "unchecked"})
  @Override
  SpecificationGroup saveAndFlush(SpecificationGroup specificationGroup);

  @Modifying
  @Query(nativeQuery = true,
      value = "UPDATE" +
          "  tbl_specification_group" +
          " SET" +
          "  user_id = :userId," +
          "  shop_id = :shopId," +
          "  receiving_and_payment_date = :receivingAndPaymentDate," +
          "  receiving_and_payment_time = :receivingAndPaymentTime," +
          "  balance_type_id = :balanceTypeId," +
          "  account_and_balance_id = :accountAndBalanceId," +
          "  group_memo = :groupMemo," +
          "  deleted = :deleted," +
          "  entry_date = :entryDate," +
          "  update_date = :updateDate" +
          " WHERE" +
          "  specification_group_id = :specificationGroupId")
  void update(
      @Param("specificationGroupId") Long specificationGroupId,
      @Param("userId") Long userId,
      @Param("shopId") Long shopId,
      @Param("receivingAndPaymentDate") LocalDate receivingAndPaymentDate,
      @Param("receivingAndPaymentTime") LocalTime receivingAndPaymentTime,
      @Param("balanceTypeId") Long balanceTypeId,
      @Param("accountAndBalanceId") Long accountAndBalanceId,
      @Param("groupMemo") String groupMemo,
      @Param("deleted") Boolean deleted,
      @Param("entryDate") LocalDateTime entryDate,
      @Param("updateDate") LocalDateTime updateDate);
}
