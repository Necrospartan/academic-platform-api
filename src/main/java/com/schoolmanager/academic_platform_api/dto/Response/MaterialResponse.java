package com.schoolmanager.academic_platform_api.dto.Response;

import com.schoolmanager.academic_platform_api.model.Material;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialResponse {
    private Long id;
    private String title;
    private String description;
    private String fileUrl;
    private String subjectName;
    private String professorName;

    public MaterialResponse( Material material) {
        this.id = material.getId();
        this.title = material.getTitle();
        this.description = material.getDescription();
        this.fileUrl = material.getFileUrl();
        this.subjectName = material.getSubject().getName();
        this.professorName = material.getProfessor().getUser().getName();
    }
}
