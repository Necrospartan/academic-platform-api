package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
    private Long id;
    private String name;

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
