package org.panda.systems.kakeipon.domain.service.user;

import org.panda.systems.kakeipon.domain.model.user.Role;
import org.panda.systems.kakeipon.domain.repository.user.RoleRepository;
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
