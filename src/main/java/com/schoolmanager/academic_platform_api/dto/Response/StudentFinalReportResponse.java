package com.schoolmanager.academic_platform_api.dto.Response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentFinalReportResponse {
    private String studentName;
    private Map<String, Float> gradesPerSubject; // key: subject name, value: grade
    private Float average;
}
