package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.UserUpdateDTO;
import com.schoolmanager.academic_platform_api.dto.Response.UserResponse;
import com.schoolmanager.academic_platform_api.model.User;

public interface UserService {
    List<UserResponse> getAllUsers();
    Optional<UserResponse> getUserById(Long id);
    Optional<UserResponse> updateUser(Long id, UserUpdateDTO user);
    boolean deleteUser(Long id);
    Optional<User> createUser(User user);
}
