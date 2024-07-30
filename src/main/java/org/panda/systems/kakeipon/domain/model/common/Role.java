package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import org.panda.systems.kakeipon.domain.model.user.User;

import java.io.Serializable;

@Entity
@Table( name = "tbl_role" )
public class Role implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @SequenceGenerator(name = "tbl_role_seq", allocationSize = 1)
    private Long roleId;

    @NotBlank
    private String roleName;


    public Long getRoleId( ) {
        return roleId;
    }

    public void setRoleId(Long roleId ) {
        this.roleId = roleId;
    }

    public String getRoleName( ) {
        return roleName;
    }

    public void setRoleName(String roleName ) {
        this.roleName = roleName;
    }
}
