package com.schoolmanager.academic_platform_api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.GradeCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.GradeUpdateDTO;
import com.schoolmanager.academic_platform_api.dto.Response.GradeResponse;
import com.schoolmanager.academic_platform_api.model.AcademicPeriod;
import com.schoolmanager.academic_platform_api.model.Grade;
import com.schoolmanager.academic_platform_api.model.Student;
import com.schoolmanager.academic_platform_api.model.Subject;
import com.schoolmanager.academic_platform_api.repository.GradeRepository;
import com.schoolmanager.academic_platform_api.repository.StudentRepository;
import com.schoolmanager.academic_platform_api.repository.SubjectRepository;
import com.schoolmanager.academic_platform_api.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService{
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional
    public Optional<GradeResponse> createGrade(GradeCreatedDTO grade) {
        Optional<Student> student = studentRepository.findById(grade.getStudentId());
        Optional<Subject> subject = subjectRepository.findById(grade.getSubjectId());
        Optional<AcademicPeriod> academicPeriod = Optional.of(subject.get().getCourse().getAcademicPeriod());

        if (student.isPresent() && subject.isPresent() && academicPeriod.isPresent()) {
            Grade newGrade = new Grade();
            newGrade.setStudent(student.get());
            newGrade.setSubject(subject.get());
            newGrade.setAcademicPeriod(academicPeriod.get());
            newGrade.setValue(grade.getValue());
            newGrade.setRemarks(grade.getRemarks());
            return Optional.of(new GradeResponse(gradeRepository.save(newGrade)));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeResponse> getAllGradeBySubject(Long subjectId) {
    return gradeRepository.findBySubjectId(subjectId).stream()
        .map(GradeResponse::new)
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GradeResponse> getAllGradeByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId).stream()
            .map(GradeResponse::new)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<GradeResponse> updateGrade(Long id, GradeUpdateDTO grade) {
        Optional<Grade> existingGrade = gradeRepository.findById(id);
        if (existingGrade.isPresent()) {
            Grade updatedGrade = existingGrade.get();
            updatedGrade.setValue(grade.getValue());
            updatedGrade.setRemarks(grade.getRemarks());
            return Optional.of(new GradeResponse(gradeRepository.save(updatedGrade)));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Boolean deleteGrade(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        if(grade.isPresent()){
            gradeRepository.delete(grade.get());
            return true;
        }
        return false;
    }
}
