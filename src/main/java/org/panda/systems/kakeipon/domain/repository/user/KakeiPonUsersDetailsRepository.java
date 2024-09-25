package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KakeiPonUsersDetailsRepository extends JpaRepository<User, String> {

  @Override
  @Query(value =
      "SELECT " +
      "  DISTINCT kpu.id, " +
      "  kpu.username, " +
      "  kpu.password, " +
      "  kpu.enabled " +
      "FROM " +
      "  users kpu " +
      "    INNER JOIN " +
      "      authorities auth " +
      "    ON " +
      "      auth.username = kpu.username " +
      "ORDER BY " +
      "  kpu.id",
      nativeQuery = true)
  List<User> findAll();

  @Query(value =
      "SELECT " +
      "  kpu.id, " +
      "  kpu.username, " +
      "  kpu.password, " +
      "  kpu.enabled, " +
      "  (SELECT " +
      "    auth.authority " +
      "  FROM" +
      "    authorities auth " +
      "  WHERE " +
      "    auth.username = kpu.username) AS authority " +
      "FROM " +
      "  kp.users kpu " +
      "WHERE " +
      "  kpu.id = ?1", nativeQuery = true)
  User findByUserAndAuthorityKeyId(Integer id);

  User findById(Integer id);

  User findByUsername(String username);

  Boolean existsByUsername(String username);

  @Query(value = "SELECT MAX(id) FROM users", nativeQuery = true)
  Integer getMaxId();

  User saveAndFlush(User entity);
}
