package com.schoolmanager.academic_platform_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolmanager.academic_platform_api.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
}
