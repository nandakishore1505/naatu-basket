package com.naatubasket.backend.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.naatubasket.backend.common.constants.ApiMessages;
import com.naatubasket.backend.common.constants.ApiPaths;
import com.naatubasket.backend.common.response.ApiResponse;
import com.naatubasket.backend.common.response.ResponseBuilder;
import com.naatubasket.backend.inventory.dto.InventoryRequest;
import com.naatubasket.backend.inventory.dto.InventoryResponse;
import com.naatubasket.backend.inventory.service.InventoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ApiPaths.INVENTORY)
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<InventoryResponse>> createInventory(
            @Valid @RequestBody InventoryRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(
                        ApiMessages.INVENTORY_CREATED,
                        inventoryService.createInventory(request)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getAllInventory() {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        ApiMessages.INVENTORY_FETCHED,
                        inventoryService.getAllInventory()));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventoryByProduct(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        ApiMessages.INVENTORY_FETCHED,
                        inventoryService.getInventoryByProduct(productId)));
    }

}