package com.naatubasket.backend.product.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.naatubasket.backend.common.constants.ApiMessages;
import com.naatubasket.backend.common.constants.ApiPaths;
import com.naatubasket.backend.common.response.ApiResponse;
import com.naatubasket.backend.common.response.ResponseBuilder;
import com.naatubasket.backend.product.dto.ProductRequest;
import com.naatubasket.backend.product.dto.ProductResponse;
import com.naatubasket.backend.product.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.PRODUCTS)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(
                        ApiMessages.PRODUCT_CREATED,
                        productService.createProduct(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            Pageable pageable) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        ApiMessages.PRODUCTS_FETCHED,
                        productService.getAllProducts(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        ApiMessages.PRODUCT_FETCHED,
                        productService.getProductById(id)));
    }

    @GetMapping("/search")
public ResponseEntity<ApiResponse<Page<ProductResponse>>> searchProducts(
        @RequestParam String keyword,
        Pageable pageable) {

    return ResponseEntity.ok(
            ResponseBuilder.success(
                    "Products searched successfully",
                    productService.searchProducts(keyword, pageable)));
}

}