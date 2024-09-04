package org.panda.systems.kakeipon.domain.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_role")
@Data
public class Role implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_role_seq", allocationSize = 1)
  @Column(name = "role_id")
  private Long roleId;

  @NotEmpty
  @Column(name = "role_name")
  private String roleName;
}
