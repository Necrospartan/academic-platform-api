package com.schoolmanager.academic_platform_api;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import com.schoolmanager.academic_platform_api.model.Role;
import com.schoolmanager.academic_platform_api.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Component
public class RoleInitializer {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {    
        List<String> roles = List.of("ADMIN", "PROFESOR", "ESTUDIANTE");
        for (String roleName : roles) {
            roleRepository.findByName(roleName)
            .orElseGet(() -> roleRepository.save(new Role(roleName)));
        }
    }
}