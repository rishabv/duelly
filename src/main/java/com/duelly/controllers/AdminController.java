package com.duelly.controllers;

import com.duelly.constants.RestApiConstant;
import com.duelly.constants.SuccessMessage;
import com.duelly.dtos.CategoryDto;
import com.duelly.dtos.responses.BaseApiResponse;
import com.duelly.entities.Category;
import com.duelly.services.admin.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(RestApiConstant.BASE_URL + RestApiConstant.ADMIN_URL)
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto body) {
        Category category = adminService.createCategory(body);
        if(category == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new BaseApiResponse<>(category, SuccessMessage.CATEGORY_CREATED));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable Long id){
        System.out.println(id);
        return ResponseEntity.ok(new BaseApiResponse<>(adminService.removeCategory(id)));
    }
}
