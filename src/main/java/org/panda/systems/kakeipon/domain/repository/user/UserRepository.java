package org.panda.systems.kakeipon.domain.repository.user;

import org.panda.systems.kakeipon.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Override
  @Query(value = "SELECT" +
      " DISTINCT tu.user_id," +
      " tu.nick_name," +
      " tu.last_name," +
      " tu.first_name," +
      " tu.password," +
      " tu.email," +
      " tu.birth_day," +
      " tu.phone_number," +
      " tu.role_id," +
      " tr.role_name," +
      " tu.entry_date," +
      " tu.update_date" +
      " FROM tbl_user tu" +
      " INNER JOIN kp.tbl_role tr on tu.role_id = tr.role_id" +
      " ORDER BY tu.user_id", nativeQuery = true)
  List<User> findAll();

  @Query(value = "SELECT" +
      " tu.user_id," +
      " tu.nick_name," +
      " tu.last_name," +
      " tu.first_name," +
      " tu.password," +
      " tu.email," +
      " tu.birth_day," +
      " tu.phone_number," +
      " tu.role_id," +
      " (select tr.role_name from tbl_role tr where tr.role_id = tu.role_id) as role_name," +
      " tu.entry_date," +
      " tu.update_date" +
      " FROM tbl_user tu" +
      " WHERE tu.user_id = ?1", nativeQuery = true)
  User findByUserAndRoleKeyId(Long userId);

  @Query(value = "SELECT MAX(user_id) FROM tbl_user", nativeQuery = true)
  Long getMaxUserId();

  @SuppressWarnings({"unchecked", "NullableProblems"})
  User saveAndFlush(User user);
}
