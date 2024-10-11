package org.panda.systems.kakeipon.domain.model.users;

import java.util.List;

public enum RoleName {
  ADMIN,
  USER;

  public static List<RoleName> getRoleNameList() {
    return List.of(ADMIN, USER);
  }
}
