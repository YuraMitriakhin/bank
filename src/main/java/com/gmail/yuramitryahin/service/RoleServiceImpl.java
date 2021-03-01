package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Role;
import com.gmail.yuramitryahin.entity.RoleType;
import com.gmail.yuramitryahin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(RoleType.valueOf(roleName.toUpperCase()));
    }
}
