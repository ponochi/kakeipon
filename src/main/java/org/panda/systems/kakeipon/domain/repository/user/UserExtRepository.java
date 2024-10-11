package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.user.UserExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExtRepository extends JpaRepository<UserExt, Integer> {
  @SuppressWarnings("NullableProblems")
  @Override
  @Query(value =
      "SELECT " +
      "  DISTINCT" +
      "    kpue.user_id, " +
      "  kpue.last_name, " +
      "  kpue.first_name, " +
      "  kpue.email, " +
      "  kpue.birth_day, " +
      "  kpue.phone_number, " +
      "  kpue.entry_date, " +
      "  kpue.update_date, " +
      "  (SELECT " +
      "    kpu.id " +
      "  FROM " +
      "    kp.users kpu " +
      "  WHERE " +
      "    kpu.user_id = kpue.user_id) AS username " +
      "FROM" +
      "  users_ext kpue " +
//      "  INNER JOIN" +
//      "    authorities auth " +
//      "  ON " +
//      "    username = auth.username " +
      "ORDER BY " +
      "  kpue.user_id", nativeQuery = true)
  List<UserExt> findAll();

  @Query(value =
      "SELECT " +
      "  kpue.user_id, " +
      "  kpue.last_name, " +
      "  kpue.first_name, " +
      "  kpue.email, " +
      "  kpue.birth_day, " +
      "  kpue.phone_number, " +
//      "  (SELECT " +
//      "    (SELECT" +
//      "      auth.authority " +
//      "    FROM" +
//      "      authorities auth" +
//      "    WHERE" +
//      "      auth.username = kpu.id) AS authority " +
//      "  FROM " +
//      "    kp.users kpu " +
//      "  WHERE " +
//      "    kpu.user_id = kpue.user_id), " +
      "  kpue.entry_date, " +
      "  kpue.update_date " +
      "FROM" +
      "  kp.users_ext kpue " +
      "WHERE" +
      "  kpue.user_id = ?1", nativeQuery = true)
  Optional<UserExt> findByUserId(Long userId);

  @Query(value = "SELECT MAX(user_id) FROM users_ext", nativeQuery = true)
  Integer getMaxId();

  @SuppressWarnings({"unchecked", "NullableProblems"})
  UserExt saveAndFlush(UserExt entity);
}
