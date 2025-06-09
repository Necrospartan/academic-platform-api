package com.schoolmanager.academic_platform_api.repository;

import com.schoolmanager.academic_platform_api.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}