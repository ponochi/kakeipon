package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.ThirdClassification;
import org.panda.systems.kakeipon.domain.model.common.ThirdClassificationEntityPkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ThirdClassificationRepository
    extends JpaRepository<ThirdClassification,
    ThirdClassificationEntityPkey> {

  @Query(value = "SELECT" +
      " tc.third_class_id," +
      " tc.second_class_id," +
      " tc.third_class_name," +
      " tcbo.order_number," +
      " tcbo.disabled" +
      " FROM third_class tc" +
      " INNER JOIN kp.third_class_by_order tcbo" +
      "   ON tc.second_class_id = tcbo.second_class_id" +
      "   AND tcbo.disabled = false" +
      " WHERE tc.third_class_id = ?1" +
      " ORDER BY tcbo.order_number", nativeQuery = true)
  ThirdClassification getThirdClassificationByThirdClassId(
      Long thirdClassId);

  @Query(value = "SELECT" +
      " tc.third_class_id," +
      " tc.second_class_id," +
      " tc.third_class_name," +
      " tcbo.order_number," +
      " tcbo.disabled" +
      " FROM third_class tc" +
      " INNER JOIN kp.third_class_by_order tcbo" +
      "   ON tc.second_class_id = tcbo.second_class_id" +
      "   AND tcbo.disabled = false" +
      " WHERE tc.second_class_id = ?1" +
      " ORDER BY tcbo.order_number", nativeQuery = true)
  List<ThirdClassification> getThirdClassificationsBySecondClassId(
      Long secondClassId);

  @Override
  @NonNull
  List<ThirdClassification> findAll();
}
