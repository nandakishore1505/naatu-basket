-- ==========================================================
-- Naatu Basket
-- Version : V6
-- Module  : Inventory
-- Description :
--      Stores stock information for each product
-- ==========================================================

------------------------------------------------------------
-- INVENTORY
------------------------------------------------------------

CREATE TABLE inventory
(
    id BIGSERIAL PRIMARY KEY,

    product_id BIGINT NOT NULL UNIQUE,

    quantity INTEGER NOT NULL DEFAULT 0,

    reserved_quantity INTEGER NOT NULL DEFAULT 0,

    reorder_level INTEGER NOT NULL DEFAULT 10,

    low_stock_threshold INTEGER NOT NULL DEFAULT 5,

    version INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP,

    CONSTRAINT fk_inventory_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
);

------------------------------------------------------------
-- INDEXES
------------------------------------------------------------

CREATE INDEX idx_inventory_product
ON inventory(product_id);

CREATE INDEX idx_inventory_quantity
ON inventory(quantity);