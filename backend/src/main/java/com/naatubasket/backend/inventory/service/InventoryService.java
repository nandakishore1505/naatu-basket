package com.naatubasket.backend.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naatubasket.backend.common.exception.DuplicateResourceException;
import com.naatubasket.backend.common.exception.ResourceNotFoundException;
import com.naatubasket.backend.inventory.dto.InventoryRequest;
import com.naatubasket.backend.inventory.dto.InventoryResponse;
import com.naatubasket.backend.inventory.entity.Inventory;
import com.naatubasket.backend.inventory.mapper.InventoryMapper;
import com.naatubasket.backend.inventory.repository.InventoryRepository;
import com.naatubasket.backend.product.entity.Product;
import com.naatubasket.backend.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryResponse createInventory(InventoryRequest request) {

        if (inventoryRepository.existsByProductId(request.getProductId())) {
            throw new DuplicateResourceException("Inventory already exists for this product.");
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found."));

        Inventory inventory = inventoryMapper.toEntity(request);

        inventory.setProduct(product);

        inventory = inventoryRepository.save(inventory);

        return inventoryMapper.toResponse(inventory);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .filter(i -> i.getDeletedAt() == null)
                .map(inventoryMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public InventoryResponse getInventoryByProduct(Long productId) {

        Inventory inventory = inventoryRepository
                .findByProductIdAndDeletedAtIsNull(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Inventory not found."));

        return inventoryMapper.toResponse(inventory);
    }
}