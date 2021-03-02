package com.gmail.yuramitryahin.repository;

import com.gmail.yuramitryahin.entity.Role;
import com.gmail.yuramitryahin.entity.RoleType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleType roleType);
}
