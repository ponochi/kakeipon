package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.SecondClassification;
import org.panda.systems.kakeipon.domain.model.common.SecondClassificationEntityPkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecondClassificationRepository
    extends JpaRepository<SecondClassification,
    SecondClassificationEntityPkey> {

  @Query(value = "SELECT" +
      " sc.second_class_id," +
      " sc.first_class_id," +
      " sc.second_class_name" +
      " FROM second_class sc" +
      " INNER JOIN kp.second_class_by_order scbo" +
      "   ON sc.second_class_id = scbo.second_class_id" +
      "   AND sc.first_class_id = scbo.first_class_id" +
      "   AND scbo.disabled = false" +
      " WHERE sc.second_class_id = ?1", nativeQuery = true)
  SecondClassification getSecondClassificationBySecondClassId(Long secondClassId);

  @Query(value = "SELECT" +
      " sc.second_class_id," +
      " sc.first_class_id," +
      " sc.second_class_name" +
      " FROM second_class sc" +
      " INNER JOIN kp.second_class_by_order scbo" +
      "   ON sc.second_class_id = scbo.second_class_id" +
      "   AND sc.first_class_id = scbo.first_class_id" +
      "   AND scbo.disabled = false" +
      " WHERE sc.first_class_id = ?1", nativeQuery = true)
  List<SecondClassification> getSecondClassificationsByFirstClassId(Long firstClassId);

  @Override
  @NonNull
  List<SecondClassification> findAll();
}
