package com.schoolmanager.academic_platform_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.academic_platform_api.model.Material;

public interface MaterialRepository extends JpaRepository <Material, Long> {
    //Material by Subject
    List<Material> findBySubjectId(Long subjectId);
}
