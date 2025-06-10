package com.schoolmanager.academic_platform_api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolmanager.academic_platform_api.dto.Response.AverageGradeResponse;
import com.schoolmanager.academic_platform_api.dto.Response.FinalCourseReportResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentFinalReportResponse;
import com.schoolmanager.academic_platform_api.dto.Response.StudentHistoryResponse;
import com.schoolmanager.academic_platform_api.model.Course;
import com.schoolmanager.academic_platform_api.model.Grade;
import com.schoolmanager.academic_platform_api.model.Student;
import com.schoolmanager.academic_platform_api.repository.CourseRepository;
import com.schoolmanager.academic_platform_api.repository.GradeRepository;
import com.schoolmanager.academic_platform_api.service.ReportService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService{
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<AverageGradeResponse> getAverageGradesByCourseAndSubject() {
        List<Grade> allGrades = gradeRepository.findAll();

        Map<String, Map<String, Double>> grouped = allGrades.stream()
            .collect(Collectors.groupingBy(
                g -> g.getSubject().getCourse().getName(),
                Collectors.groupingBy(
                    g -> g.getSubject().getName(),
                    Collectors.averagingDouble(g -> g.getValue())
                )
            ));

        List<AverageGradeResponse> results = new ArrayList<>();
        grouped.forEach((courseName, subjects) -> {
            subjects.forEach((subjectName, avg) -> {
                results.add(new AverageGradeResponse(courseName, subjectName, avg));
            });
        });

        return results;
    }

    @Override
    public List<StudentHistoryResponse> getGradeHistoryByStudent(Long studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId);
        return grades.stream()
            .map(g -> new StudentHistoryResponse(
                g.getSubject().getName(),
                g.getValue(),
                g.getSubject().getCourse().getName(),
                g.getAcademicPeriod().getName()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public FinalCourseReportResponse getFinalReportByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

        String academicPeriodName = course.getAcademicPeriod().getName();

        List<Grade> grades = gradeRepository.findByCourseId(courseId);

        Map<Student, Map<String, List<Float>>> studentGradesMap = new HashMap<>();

        for (Grade grade : grades) {
            Student student = grade.getStudent();
            String subjectName = grade.getSubject().getName();
            Float value = grade.getValue();

            studentGradesMap
                .computeIfAbsent(student, _ -> new HashMap<>())
                .computeIfAbsent(subjectName, _ -> new ArrayList<>())
                .add(value);
        }

        List<StudentFinalReportResponse> studentFinalReports = new ArrayList<>();

        for (Map.Entry<Student, Map<String, List<Float>>> entry : studentGradesMap.entrySet()) {
            Student student = entry.getKey();
            Map<String, List<Float>> subjectGrades = entry.getValue();

            Map<String, Float> gradesPerSubject = new HashMap<>();
            float sumAllGrades = 0f;
            int countAllGrades = 0;

            for (Map.Entry<String, List<Float>> sgEntry : subjectGrades.entrySet()) {
                List<Float> subjectGradeList = sgEntry.getValue();
                float avgSubject = (float) subjectGradeList.stream()
                    .mapToDouble(Float::doubleValue)
                    .average()
                    .orElse(0);
                gradesPerSubject.put(sgEntry.getKey(), avgSubject);

                sumAllGrades += avgSubject;
                countAllGrades++;
            }

            float average = countAllGrades > 0 ? sumAllGrades / countAllGrades : 0;

            studentFinalReports.add(new StudentFinalReportResponse(
                student.getUser().getName(),
                gradesPerSubject,
                average
            ));
        }

        return new FinalCourseReportResponse(
            course.getName(),
            academicPeriodName,
            studentFinalReports
        );
    }
}
