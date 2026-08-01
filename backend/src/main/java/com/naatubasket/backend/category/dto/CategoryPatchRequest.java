package com.naatubasket.backend.category.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Partial update payload. Every field is optional and a null value means
 * "leave unchanged", which is what distinguishes PATCH from PUT here.
 */
@Data
public class CategoryPatchRequest {

    @Size(min = 1, max = 100, message = "Category name must be between 1 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    private String imageUrl;

    private Long parentCategoryId;

    @Min(value = 0, message = "Display order cannot be negative")
    private Integer displayOrder;

    private Boolean active;

}
