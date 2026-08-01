package com.naatubasket.backend.category.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naatubasket.backend.category.dto.CategoryPatchRequest;
import com.naatubasket.backend.category.dto.CategoryRequest;
import com.naatubasket.backend.category.dto.CategoryResponse;
import com.naatubasket.backend.category.service.CategoryService;
import com.naatubasket.backend.common.config.WebConfig;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Reads are public so storefronts can browse the catalogue without a token.
 * Writes require an authenticated ADMIN.
 *
 * <p>The {@code /api/v1} prefix is applied globally by {@link WebConfig}.</p>
 */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Product category catalogue")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a category")
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest request) {

        CategoryResponse created = categoryService.createCategory(request);

        return ResponseEntity
                .created(URI.create(WebConfig.API_PREFIX + "/categories/" + created.getId()))
                .body(created);
    }

    @GetMapping
    @Operation(summary = "List categories, optionally only top-level ones")
    public List<CategoryResponse> getAllCategories(
            @RequestParam(name = "rootOnly", defaultValue = "false") boolean rootOnly) {

        return rootOnly
                ? categoryService.getRootCategories()
                : categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch a single category")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{id}/children")
    @Operation(summary = "List the direct subcategories of a category")
    public List<CategoryResponse> getChildren(@PathVariable Long id) {
        return categoryService.getChildCategories(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Replace a category. Omitted optional fields are cleared.")
    public CategoryResponse updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {

        return categoryService.updateCategory(id, request);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Partially update a category. Only supplied fields change.")
    public CategoryResponse patchCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryPatchRequest request) {

        return categoryService.patchCategory(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Soft delete a category")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
