package org.panda.systems.kakeipon.domain.repository.users;

import org.panda.systems.kakeipon.domain.model.users.Users;
import org.panda.systems.kakeipon.domain.service.users.UsersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersDetailRepository extends JpaRepository<Users, String> {

  @SuppressWarnings("NullableProblems")
  @Override
  @Query(value =
      "SELECT " +
      "   DISTINCT kpu.id," +
      "   kpu.user_id," +
      "   kpu.password," +
      "   kpu.role_name," +
      "   kpu.enabled," +
      "   kpu.account_non_expired," +
      "   kpu.account_non_locked, " +
      "   kpu.credentials_non_expired" +
      " FROM" +
      "   users kpu" +
      " ORDER BY" +
      "   kpu.id",
      nativeQuery = true)
  List<Users> findAll();

  @Query(value =
      "SELECT " +
      "   kpu.user_id," +
      "   kpu.id," +
      "   kpu.password," +
      "   kpu.role_name," +
      "   kpu.enabled," +
      "   kpu.account_non_expired," +
      "   kpu.account_non_locked, " +
      "   kpu.credentials_non_expired" +
      " FROM" +
      "  kp.users kpu " +
      "WHERE " +
      "  kpu.id = ?1", nativeQuery = true)
  Users findById(Integer id);

  @Query(value =
      "SELECT" +
      "   kpu.user_id," +
      "   kpu.id," +
      "   kpu.password," +
      "   kpu.role_name," +
      "   kpu.enabled," +
      "   kpu.account_non_expired," +
      "   kpu.account_non_locked, " +
      "   kpu.credentials_non_expired" +
      " FROM" +
      "   kp.users kpu" +
      " WHERE" +
      "   kpu.user_id = ?1", nativeQuery = true)
  UsersDetail findByUserId(Long userId);

  boolean existsById(String id);

  @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
  Integer getMaxId();

  @SuppressWarnings({"unchecked", "NullableProblems"})
  Users saveAndFlush(Users entity);
}
