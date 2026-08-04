-- ==========================================================
-- Naatu Basket
-- Version : V5
-- Module  : Products
-- Description :
--      Creates products table
-- ==========================================================

------------------------------------------------------------
-- PRODUCTS
------------------------------------------------------------

CREATE TABLE products
(
    id BIGSERIAL PRIMARY KEY,

    category_id BIGINT NOT NULL,

    name VARCHAR(200) NOT NULL,

    description TEXT,

    sku VARCHAR(100) NOT NULL UNIQUE,

    barcode VARCHAR(100),

    brand VARCHAR(100),

    unit VARCHAR(20) NOT NULL,

    mrp DECIMAL(10,2) NOT NULL,

    selling_price DECIMAL(10,2) NOT NULL,

    image_url VARCHAR(500),

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    version INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP,

    CONSTRAINT fk_product_category
        FOREIGN KEY(category_id)
        REFERENCES categories(id)
);

------------------------------------------------------------
-- INDEXES
------------------------------------------------------------

CREATE INDEX idx_products_category
ON products(category_id);

CREATE INDEX idx_products_name
ON products(name);

CREATE INDEX idx_products_sku
ON products(sku);

CREATE INDEX idx_products_barcode
ON products(barcode);

------------------------------------------------------------
-- SAMPLE DATA
------------------------------------------------------------

INSERT INTO products
(
    category_id,
    name,
    description,
    sku,
    barcode,
    brand,
    unit,
    mrp,
    selling_price
)
VALUES
(
    1,
    'Apple',
    'Fresh Kashmir Apple',
    'FR-APPLE-001',
    '890100000001',
    'Naatu Fresh',
    'KG',
    220.00,
    199.00
),
(
    2,
    'Tomato',
    'Farm Fresh Tomato',
    'VE-TOMATO-001',
    '890100000002',
    'Naatu Fresh',
    'KG',
    50.00,
    42.00
),
(
    3,
    'Milk',
    'Full Cream Milk',
    'DA-MILK-001',
    '890100000003',
    'Amul',
    'LITER',
    68.00,
    66.00
);