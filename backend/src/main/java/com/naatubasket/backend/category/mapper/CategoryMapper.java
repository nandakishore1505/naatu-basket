package com.naatubasket.backend.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.naatubasket.backend.category.dto.CategoryRequest;
import com.naatubasket.backend.category.dto.CategoryResponse;
import com.naatubasket.backend.category.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    Category toEntity(CategoryRequest request);

    @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    CategoryResponse toResponse(Category category);
}