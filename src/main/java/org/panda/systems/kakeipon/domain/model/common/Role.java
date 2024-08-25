package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tbl_role")
@Data
public class Role implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_role_seq", allocationSize = 1)
  @Column(name = "role_id")
  private Long roleId;

  @NotEmpty
  @Column
  private String roleName;
}
