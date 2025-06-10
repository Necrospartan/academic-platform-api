package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.ProfessorCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.ProfessorResponse;
import com.schoolmanager.academic_platform_api.model.Professor;
import com.schoolmanager.academic_platform_api.model.Role;
import com.schoolmanager.academic_platform_api.model.Subject;
import com.schoolmanager.academic_platform_api.model.User;
import com.schoolmanager.academic_platform_api.repository.ProfessorRepository;
import com.schoolmanager.academic_platform_api.repository.RoleRepository;
import com.schoolmanager.academic_platform_api.service.ProfessorService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService{
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProfessorResponse> getAllProfessorResponses() {
        return professorRepository.findAll().stream()
        .map(ProfessorResponse::new)
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProfessorResponse> getProfessorResponseById(Long id) {
        return professorRepository.findById(id)
        .map(ProfessorResponse::new);
    }

    @Override
    @Transactional
    public Optional<Professor> createProfessor(ProfessorCreatedDTO request) {
        // Crear usuario
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        Role role = roleRepository.findByName("PROFESOR")
        .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        
        User savedUser = userService.createUser(user).orElseThrow(() -> new RuntimeException("User creation failed"));
        // Crear profesor
        Professor professor = new Professor();
        professor.setUser(savedUser);
        professor.setSpecialty(request.getSpecialty());
    
        return Optional.of(professorRepository.save(professor));
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getSubjectsByProfessor(Long id){
        return professorRepository.findById(id)
                .map(professor -> professor.getSubject().stream()
                        .map(Subject::getName) 
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("Professor not found with id: " + id));
    }
}
