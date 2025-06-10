package com.schoolmanager.academic_platform_api.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AverageGradeResponse {
    private String courseName;
    private String subjectName;
    private double averageGrade;
}
