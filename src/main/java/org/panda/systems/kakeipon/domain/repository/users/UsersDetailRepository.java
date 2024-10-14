package org.panda.systems.kakeipon.domain.repository.users;

import org.panda.systems.kakeipon.domain.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersDetailRepository extends JpaRepository<Users, String> {

  @SuppressWarnings("NullableProblems")
  @Override
  @Query(value =
      "SELECT " +
      "   kpu.user_id," +
      "   kpu.username," +
      "   kpu.password," +
      "   kpu.role_name," +
      "   kpu.enabled," +
      "   kpu.account_non_expired," +
      "   kpu.account_non_locked, " +
      "   kpu.credentials_non_expired" +
      " FROM" +
      "   users kpu" +
      " ORDER BY" +
      "   kpu.username",
      nativeQuery = true)
  List<Users> findAll();

  @Query(value =
      "SELECT " +
      "   kpu.user_id," +
      "   kpu.username," +
      "   kpu.password," +
      "   kpu.role_name," +
      "   kpu.enabled," +
      "   kpu.account_non_expired," +
      "   kpu.account_non_locked, " +
      "   kpu.credentials_non_expired" +
      " FROM" +
      "  kp.users kpu " +
      "WHERE " +
      "  kpu.username = ?1", nativeQuery = true)
  Users findByUsername(String username);

  @Query(value =
      "SELECT" +
      "   kpu.user_id," +
      "   kpu.username," +
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
  Users findByUserId(Long userId);

  boolean existsByUserId(Long userId);
  boolean existsByUsername(String username);

  @Query(value = "SELECT MAX(user_id) FROM users", nativeQuery = true)
  Integer getMaxUserId();

  @SuppressWarnings({"unchecked", "NullableProblems"})
  Users saveAndFlush(Users entity);
}
