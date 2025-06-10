package com.schoolmanager.academic_platform_api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.SubjectCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.SubjectResponse;
import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.model.Professor;
import com.schoolmanager.academic_platform_api.model.Subject;
import com.schoolmanager.academic_platform_api.repository.CourseRepository;
import com.schoolmanager.academic_platform_api.repository.ProfessorRepository;
import com.schoolmanager.academic_platform_api.repository.SubjectRepository;
import com.schoolmanager.academic_platform_api.service.SubjectService;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SubjectResponse> getAllSubject() {
        return subjectRepository.findAll().stream()
            .map(SubjectResponse::new)
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SubjectResponse> getSubjectById(Long id) {
        return subjectRepository.findById(id)
            .map(SubjectResponse::new);
    }

    @Override
    @Transactional
    public Optional<SubjectResponse> createSubject(SubjectCreatedDTO subject) {
        Subject newSubject = new Subject();
        newSubject.setName(subject.getName());
        Optional<Professor> professor = professorRepository.findById(subject.getProfessorId());
        if(professor.isEmpty()){
            throw new RuntimeException("Professor not found with id: " + subject.getProfessorId());
        }
        newSubject.setProfessor(professor.get());
        Optional<Course> course = courseRepository.findById(subject.getCourseId());
        if(course.isEmpty()){
            throw new RuntimeException("Course not found with id: " + subject.getCourseId());
        }
        newSubject.setCourse(course.get());
        return Optional.of(new SubjectResponse(subjectRepository.save(newSubject)));
    }

    @Override
    @Transactional
    public Optional<Subject> updateSubject(Long id, SubjectCreatedDTO subject) {
        Subject existingSubject = subjectRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        existingSubject.setName(subject.getName());
        existingSubject.setProfessor(professorRepository.findById(subject.getProfessorId())
            .orElseThrow(() -> new RuntimeException("Professor not found with id: " + subject.getProfessorId())));
        existingSubject.setCourse(courseRepository.findById(subject.getCourseId())
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + subject.getCourseId())));
        return Optional.of(subjectRepository.save(existingSubject));
    }

    @Override
    @Transactional
    public boolean deleteSubject(Long id) {
        if(subjectRepository.existsById(id)){
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
