package com.schoolmanager.academic_platform_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.academic_platform_api.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long>{
    List<Grade> findBySubjectId(Long subjectId);
    List<Grade> findByStudentId(Long studentId);
}
