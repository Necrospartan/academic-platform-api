package com.schoolmanager.academic_platform_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmanager.academic_platform_api.dto.MaterialCreatedDTO;
import com.schoolmanager.academic_platform_api.dto.Response.MaterialResponse;
import com.schoolmanager.academic_platform_api.service.MaterialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping("/Subject/{id}")
    public ResponseEntity<List<MaterialResponse>> getAllMaterialBySubject(@PathVariable Long id) {
        List<MaterialResponse> materials = materialService.getAllMaterialBySubject(id);
    
        if (materials.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        return ResponseEntity.ok(materials);
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> createMaterial(@RequestBody @Valid MaterialCreatedDTO material) {
        return materialService.createMaterial(material)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.internalServerError().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        Boolean deleted = materialService.deleteMaterial(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
