package com.gmail.yuramitryahin.service;

import com.gmail.yuramitryahin.entity.Role;
import com.gmail.yuramitryahin.entity.RoleType;
import com.gmail.yuramitryahin.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(RoleType.valueOf(roleName.toUpperCase())).get();
    }
}
