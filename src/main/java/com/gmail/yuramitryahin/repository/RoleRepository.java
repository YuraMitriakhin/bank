package com.gmail.yuramitryahin.repository;

import com.gmail.yuramitryahin.entity.Role;
import com.gmail.yuramitryahin.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleType roleType);
}
