package com.schoolmanager.academic_platform_api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.MaterialCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.MaterialResponse;
import com.schoolmanager.academic_platform_api.model.Material;
import com.schoolmanager.academic_platform_api.model.Professor;
import com.schoolmanager.academic_platform_api.model.Subject;
import com.schoolmanager.academic_platform_api.repository.MaterialRepository;
import com.schoolmanager.academic_platform_api.repository.ProfessorRepository;
import com.schoolmanager.academic_platform_api.repository.SubjectRepository;
import com.schoolmanager.academic_platform_api.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MaterialResponse> getAllMaterialBySubject(Long subjectId){
        return materialRepository.findBySubjectId(subjectId).stream()
        .map(MaterialResponse::new)
        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<MaterialResponse> createMaterial(MaterialCreatedDTO material) {
        Optional<Subject> subject = subjectRepository.findById(material.getSubjectId());

        Optional<Professor> professor = professorRepository.findById(material.getProfessorId());

        if(subject.isPresent() && professor.isPresent()){
            Material newMaterial = new Material();

            newMaterial.setTitle(material.getTitle());
            newMaterial.setDescription(material.getDescription());
            newMaterial.setFileUrl(material.getFileUrl());
            newMaterial.setSubject(subject.get());
            newMaterial.setProfessor(professor.get());
            materialRepository.save(newMaterial);
            return Optional.of(new MaterialResponse(newMaterial));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Boolean deleteMaterial(Long id) {
        Optional<Material> material = materialRepository.findById(id);

        if(material.isPresent()){
            materialRepository.delete(material.get());
            return true;
        }
        return false;
    }
}
