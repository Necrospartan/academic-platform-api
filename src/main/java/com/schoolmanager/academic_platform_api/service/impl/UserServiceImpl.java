package com.schoolmanager.academic_platform_api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.UserUpdateDTO;
import com.schoolmanager.academic_platform_api.dto.Response.UserResponse;
import com.schoolmanager.academic_platform_api.model.User;
import com.schoolmanager.academic_platform_api.repository.UserRepository;
import com.schoolmanager.academic_platform_api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::new)
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(UserResponse::new);
    }

    @Override
    public Optional<UserResponse> updateUser(Long id, UserUpdateDTO user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);
            return Optional.of(new UserResponse(existingUser));
        } else {
            return Optional.empty();
        }
        
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            return Optional.empty();
        } else {
            return Optional.of(userRepository.save(user));
        }
    }
}
