package com.schoolmanager.academic_platform_api.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentHistoryResponse {
    private String subjectName;
    private float value;
    private String courseName;
    private String periodName;
}
