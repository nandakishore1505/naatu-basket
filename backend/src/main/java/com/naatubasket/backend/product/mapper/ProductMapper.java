package com.naatubasket.backend.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.naatubasket.backend.product.dto.ProductRequest;
import com.naatubasket.backend.product.dto.ProductResponse;
import com.naatubasket.backend.product.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "version", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponse toResponse(Product product);
}