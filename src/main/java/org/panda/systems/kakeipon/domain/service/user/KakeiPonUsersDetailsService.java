package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.app.user.UserForm;
import org.panda.systems.kakeipon.domain.model.user.Authorities;
import org.panda.systems.kakeipon.domain.model.user.User;
import org.panda.systems.kakeipon.domain.repository.user.KakeiPonUsersDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class KakeiPonUsersDetailsService implements UserDetailsService {
  @Autowired
  public KakeiPonUsersDetailsRepository kakeiPonUsersDetailsRepository;
  @Autowired
  private AuthoritiesService authoritiesService;

  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    User user = kakeiPonUsersDetailsRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
        // パスワード
        .password(user.getPassword())
        // 役割を設定（rolesも引数の指定方法が変わるが、権限に使える、権限はStringをカンマ区切りで複数指定できる）
        .authorities(user.getAuthorities().toString())
        // 無効なアカウントはログインさせない(del_flgが1の場合、論理削除）
        //.disabled("1".equals(users.getEnabled())
        // アカウントが有効期限が今日より過去の場合はtrueにして期限切れにする
        //.accountExpired(users.getExpiration().before(new Date()))
        // パスワードの有効期限が今日より過去の場合はtrueにして期限切れにする
        //.credentialsExpired(account.getPasswordExpiration().before(new Date()))
        // ログイン失敗回数lockingBoundaries回以上でロックする
        //.accountLocked(account.getLoginFailureCount() >= lockingBoundaries)
        // 上で設定した情報をもったUserDetailsを作成
        .build();
  }

  public List<User> findAll() {
    return kakeiPonUsersDetailsRepository.findAll();
  }

  public User findByUserAndAuthorityKeyId(Integer id) {
    return kakeiPonUsersDetailsRepository
        .findByUserAndAuthorityKeyId(id);
  }

  public UserForm findByIdToForm(Integer id) {
    UserForm userForm = new UserForm();

    User userDetails
        = kakeiPonUsersDetailsRepository.findByUserAndAuthorityKeyId(id);
    Authorities authorities
        = authoritiesService.findByUsername(
            userDetails.getUsername());

    userForm.setId(userDetails.getId());
    userForm.setUsername(userDetails.getUsername());
    userForm.setPassword(userDetails.getPassword());
    userForm.setAuthoritiesToForm(authorities);
    userForm.setEnabled(userDetails.getEnabled());
    userForm.setAccountNonExpired(true);
    userForm.setAccountNonLocked(true);
    userForm.setCredentialsNonExpired(true);

    return userForm;
  }

  public User findById(Integer id) {
    return kakeiPonUsersDetailsRepository.findById(id);
  }

  public User findByUsername(String username) {
    return kakeiPonUsersDetailsRepository.findByUsername(username);
  }

  public Integer convertUsernameToId(String username) {
    User user = kakeiPonUsersDetailsRepository.findByUsername(username);
    return user.getId();
  }

  public Boolean existsByUsername(String username) {
    return kakeiPonUsersDetailsRepository.existsByUsername(username);
  }

  public Integer getMaxId() {
    return kakeiPonUsersDetailsRepository.getMaxId();
  }

  public List<UserForm> findAllUsersToForm() {
    List<User> users
        = kakeiPonUsersDetailsRepository.findAll();

    List<UserForm> userForms = new ArrayList<>();
    for (User user : users) {
      UserForm userForm = new UserForm();
      userForm.setId(user.getId());
      userForm.setUsername(user.getUsername());
      userForm.setPassword(user.getPassword());
      userForm.setEnabled(true);
      userForm.setAccountNonExpired(true);
      userForm.setAccountNonLocked(true);
      userForm.setCredentialsNonExpired(true);
      userForms.add(userForm);
    }
    return userForms;
  }

  @Transactional
  public User saveAndFlush(User entity) {
    return kakeiPonUsersDetailsRepository.saveAndFlush(entity);
  }
}
