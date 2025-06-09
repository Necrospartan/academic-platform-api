package com.schoolmanager.academic_platform_api.service;

import java.util.List;
import java.util.Optional;

import com.schoolmanager.academic_platform_api.dto.UserUpdateDTO;
import com.schoolmanager.academic_platform_api.model.User;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> updateUser(Long id, UserUpdateDTO user);
    boolean deleteUser(Long id);
}
