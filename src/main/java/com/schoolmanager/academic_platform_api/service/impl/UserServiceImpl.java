package com.schoolmanager.academic_platform_api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.UserUpdateDTO;
import com.schoolmanager.academic_platform_api.model.User;
import com.schoolmanager.academic_platform_api.repository.UserRepository;
import com.schoolmanager.academic_platform_api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, UserUpdateDTO user) {
        return userRepository.findById(id)
        .map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return userRepository.save(existingUser);
        });
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
        return Optional.of(userRepository.save(user));
    }
}
