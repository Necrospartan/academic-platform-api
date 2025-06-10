package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.AcademicPeriod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcademicPeriodResponse {
    private Long id;
    private String name;
    private String startDate;
    private String endDate;

    public AcademicPeriodResponse(AcademicPeriod academicPeriod) {
        this.id = academicPeriod.getId();
        this.name = academicPeriod.getName();
        this.startDate = academicPeriod.getStartDate().toString();
        this.endDate = academicPeriod.getEndDate().toString();
    }
}
