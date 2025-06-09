package com.schoolmanager.academic_platform_api.dto;

import com.schoolmanager.academic_platform_api.model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {
    private Long id;

    // User fields
    private String userName;
    private String userEmail;
    private Role userRole;
}
