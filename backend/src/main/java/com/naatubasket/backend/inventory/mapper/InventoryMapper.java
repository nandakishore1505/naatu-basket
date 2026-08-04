package com.naatubasket.backend.inventory.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.naatubasket.backend.inventory.dto.InventoryRequest;
import com.naatubasket.backend.inventory.dto.InventoryResponse;
import com.naatubasket.backend.inventory.entity.Inventory;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "version", ignore = true)
    Inventory toEntity(InventoryRequest request);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    InventoryResponse toResponse(Inventory inventory);

}