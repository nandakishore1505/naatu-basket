package com.naatubasket.backend.category.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.naatubasket.backend.category.dto.CategoryRequest;
import com.naatubasket.backend.category.dto.CategoryResponse;
import com.naatubasket.backend.category.entity.Category;
import com.naatubasket.backend.category.mapper.CategoryMapper;
import com.naatubasket.backend.category.repository.CategoryRepository;
import com.naatubasket.backend.common.exception.DuplicateResourceException;
import com.naatubasket.backend.common.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // Create Category
    public CategoryResponse createCategory(CategoryRequest request) {

        if (categoryRepository.existsByNameIgnoreCaseAndDeletedAtIsNull(request.getName())) {
            throw new DuplicateResourceException(
                    "Category '" + request.getName() + "' already exists");
        }

        Category category = categoryMapper.toEntity(request);

        category = categoryRepository.save(category);

        return categoryMapper.toResponse(category);
    }

    // Get All Categories
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findByDeletedAtIsNull()
                .stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Get Category By Id
    public CategoryResponse getCategoryById(Long id) {

        Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category with id " + id + " not found"));

        return categoryMapper.toResponse(category);
    }

    // Update Category
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category with id " + id + " not found"));

        if (categoryRepository.existsByNameIgnoreCaseAndDeletedAtIsNull(request.getName())
                && !category.getName().equalsIgnoreCase(request.getName())) {

            throw new DuplicateResourceException(
                    "Category '" + request.getName() + "' already exists");
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImageUrl(request.getImageUrl());
        category.setDisplayOrder(request.getDisplayOrder());
        category.setActive(request.getActive());

        category = categoryRepository.save(category);

        return categoryMapper.toResponse(category);
    }

    // Soft Delete Category
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category with id " + id + " not found"));

        category.setDeletedAt(LocalDateTime.now());

        categoryRepository.save(category);
    }

}