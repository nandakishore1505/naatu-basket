package com.naatubasket.backend.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.naatubasket.backend.category.dto.CategoryRequest;
import com.naatubasket.backend.category.dto.CategoryResponse;
import com.naatubasket.backend.category.service.CategoryService;
import com.naatubasket.backend.common.constants.ApiMessages;
import com.naatubasket.backend.common.constants.ApiPaths;
import com.naatubasket.backend.common.response.ApiResponse;
import com.naatubasket.backend.common.response.ResponseBuilder;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(
                        ApiMessages.CATEGORY_CREATED,
                        categoryService.createCategory(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories() {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        ApiMessages.CATEGORIES_FETCHED,
                        categoryService.getAllCategories()));
    }

}