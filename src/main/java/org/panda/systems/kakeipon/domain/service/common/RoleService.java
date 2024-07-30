package org.panda.systems.kakeipon.domain.service.common;

import org.panda.systems.kakeipon.domain.model.common.Role;
import org.panda.systems.kakeipon.domain.repository.common.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public RoleService( ) {
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findByRoleId(Long roleId) {
        return roleRepository.findByRoleId(roleId);
    }
}
