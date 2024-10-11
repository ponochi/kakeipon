package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.service.user.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersDetailsRepository extends JpaRepository<User, String> {

  @SuppressWarnings("NullableProblems")
  @Override
  @Query(value =
      "SELECT " +
      "  DISTINCT kpu.id, " +
      "  kpu.id, " +
      "  kpu.password, " +
      "  kpu.enabled " +
      "FROM " +
      "  users kpu " +
//      "    INNER JOIN " +
//      "      authorities auth " +
//      "    ON " +
//      "      auth.username = kpu.id " +
      "ORDER BY " +
      "  kpu.id",
      nativeQuery = true)
  List<User> findAll();

  @Query(value =
      "SELECT " +
      "  kpu.id, " +
      "  kpu.id, " +
      "  kpu.password, " +
      "  kpu.enabled " +
//      "  (SELECT " +
//      "    auth.authority " +
//      "  FROM" +
//      "    authorities auth " +
//      "  WHERE " +
//      "    auth.username = kpu.id) AS authority " +
      "FROM " +
      "  kp.users kpu " +
      "WHERE " +
      "  kpu.id = ?1", nativeQuery = true)
  User findByUserAndAuthorityKeyId(Integer id);

  @Query(value =
      "SELECT" +
      "   kpu.id," +
      "   kpu.password," +
      "   kpu.enabled" +
      " FROM" +
      "   kp.users kpu" +
      " WHERE" +
      "   kpu.user_id = ?1", nativeQuery = true)
  UsersDetails findByUserId(Long userId);

//  UsersDetails findById(String id);

//  UsersDetails findById(String id);

  boolean existsById(String id);

  @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
  Integer getMaxId();

  @SuppressWarnings({"unchecked", "NullableProblems"})
  User saveAndFlush(User entity);
}
