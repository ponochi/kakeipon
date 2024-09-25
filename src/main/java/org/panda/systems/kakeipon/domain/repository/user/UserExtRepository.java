package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.user.UserExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExtRepository extends JpaRepository<UserExt, Integer> {
  @Override
  @Query(value =
      "SELECT " +
      "  DISTINCT" +
      "    kpue.id, " +
      "  kpue.last_name, " +
      "  kpue.first_name, " +
      "  kpue.email, " +
      "  kpue.birth_day, " +
      "  kpue.phone_number, " +
      "  kpue.entry_date, " +
      "  kpue.update_date, " +
      "  (SELECT " +
      "    kpu.username " +
      "  FROM " +
      "    kp.users kpu " +
      "  WHERE " +
      "    kpu.id = kpue.id) username " +
      "FROM" +
      "  users_ext kpue " +
      "  INNER JOIN" +
      "    authorities auth " +
      "  ON " +
      "    username = auth.username " +
      "ORDER BY " +
      "  kpue.id", nativeQuery = true)
  List<UserExt> findAll();

  @Query(value =
      "SELECT " +
      "  kpue.id, " +
      "  kpue.last_name, " +
      "  kpue.first_name, " +
      "  kpue.email, " +
      "  kpue.birth_day, " +
      "  kpue.phone_number, " +
      "  (SELECT " +
      "    (SELECT" +
      "      auth.authority " +
      "    FROM" +
      "      authorities auth" +
      "    WHERE" +
      "      auth.username = kpu.username) AS authority " +
      "  FROM " +
      "    kp.users kpu " +
      "  WHERE " +
      "    kpu.id = kpue.id), " +
      "  kpue.entry_date, " +
      "  kpue.update_date " +
      "FROM" +
      "  kp.users_ext kpue " +
      "WHERE" +
      "  kpue.id = ?1", nativeQuery = true)
  Optional<UserExt> findById(Integer id);

  @Query(value = "SELECT MAX(id) FROM users_ext", nativeQuery = true)
  Integer getMaxId();

  UserExt saveAndFlush(UserExt entity);
}
