package com.schoolmanager.academic_platform_api.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentGradeResponse {
    private Float value;
    private String subjectName;
}
