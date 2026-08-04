package com.naatubasket.backend.product.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.naatubasket.backend.category.entity.Category;
import com.naatubasket.backend.category.repository.CategoryRepository;
import com.naatubasket.backend.common.exception.DuplicateResourceException;
import com.naatubasket.backend.common.exception.ResourceNotFoundException;
import com.naatubasket.backend.product.dto.ProductRequest;
import com.naatubasket.backend.product.dto.ProductResponse;
import com.naatubasket.backend.product.entity.Product;
import com.naatubasket.backend.product.mapper.ProductMapper;
import com.naatubasket.backend.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException("SKU already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found."));

        Product product = productMapper.toEntity(request);

        product.setCategory(category);

        product = productRepository.save(product);

        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
public Page<ProductResponse> getAllProducts(Pageable pageable) {

    return productRepository
            .findByDeletedAtIsNull(pageable)
            .map(productMapper::toResponse);
}

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found."));

        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
public Page<ProductResponse> searchProducts(
        String keyword,
        Pageable pageable) {

    return productRepository
            .findByNameContainingIgnoreCaseAndDeletedAtIsNull(
                    keyword,
                    pageable)
            .map(productMapper::toResponse);
}

}