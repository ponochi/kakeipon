package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.user.Role;
import org.panda.systems.kakeipon.domain.service.user.RoleService;

import java.io.Serializable;

@Table(name = "tbl_role")
@Data
public class RoleForm implements Serializable {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_role_seq", allocationSize = 1)
  @Column(name = "role_id")
  private Long roleId;

  @Column(name = "role_name")
  private String roleName;

  public RoleForm() {

  }

  public RoleForm(RoleService service, Long roleId) {
    if (roleId == null) {
      this.setRoleId(Long.parseLong("2"));
    } else {
      this.setRoleId(roleId);
    }
    Role role = service.findByRoleId(roleId);
    this.setRoleName(role.getRoleName());
  }
}
