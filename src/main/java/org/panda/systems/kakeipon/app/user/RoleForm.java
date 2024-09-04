package org.panda.systems.kakeipon.app.user;

import jakarta.persistence.*;
import lombok.Data;

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
}
