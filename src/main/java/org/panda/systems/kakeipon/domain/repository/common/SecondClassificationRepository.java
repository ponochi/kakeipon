package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.SecondClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondClassificationRepository
    extends JpaRepository<SecondClassification, Long> {

  @Query(value = "SELECT" +
      " sc.second_class_id," +
      " sc.first_class_id," +
      " sc.second_class_name," +
      " FROM tbl_second_classification sc" +
      " INNER JOIN kp.tbl_second_class_by_order scbo" +
      "   ON sc.second_class_id = scbo.second_class_id" +
      "   AND sc.first_class_id = scbo.first_class_id" +
      "   AND scbo.disabled = false" +
      " WHERE sc.second_class_id = ?1", nativeQuery = true)
  SecondClassification getSecondClassificationBySecondClassId(Long secondClassId);

  @Query(value = "SELECT" +
      " sc.second_class_id," +
      " sc.first_class_id," +
      " sc.second_class_name," +
      " FROM tbl_second_classification sc" +
      " INNER JOIN kp.tbl_second_class_by_order scbo" +
      "   ON sc.second_class_id = scbo.second_class_id" +
      "   AND sc.first_class_id = scbo.first_class_id" +
      "   AND scbo.disabled = false" +
      " WHERE sc.first_class_id = ?1", nativeQuery = true)
  List<SecondClassification> getSecondClassificationsByFirstClassId(Long firstClassId);
}
