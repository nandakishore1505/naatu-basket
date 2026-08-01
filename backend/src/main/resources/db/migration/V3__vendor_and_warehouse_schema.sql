-- ==========================================================
-- Naatu Basket
-- Version : V3
-- Module  : Vendor & Warehouse
-- ==========================================================

------------------------------------------------------------
-- VENDORS
------------------------------------------------------------

CREATE TABLE vendors
(
    id BIGSERIAL PRIMARY KEY,

    business_name VARCHAR(200) NOT NULL,

    contact_person VARCHAR(150),

    phone_number VARCHAR(15) NOT NULL,

    email VARCHAR(255),

    gst_number VARCHAR(20),

    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP
);

------------------------------------------------------------
-- WAREHOUSES
------------------------------------------------------------

CREATE TABLE warehouses
(
    id BIGSERIAL PRIMARY KEY,

    vendor_id BIGINT NOT NULL,

    warehouse_name VARCHAR(150) NOT NULL,

    address TEXT NOT NULL,

    city VARCHAR(100) NOT NULL,

    state VARCHAR(100) NOT NULL,

    pincode VARCHAR(10) NOT NULL,

    latitude DECIMAL(10,7),

    longitude DECIMAL(10,7),

    service_radius_km INTEGER NOT NULL DEFAULT 10,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    version INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP,

    CONSTRAINT fk_warehouse_vendor
        FOREIGN KEY(vendor_id)
        REFERENCES vendors(id)
);

------------------------------------------------------------
-- INDEXES
------------------------------------------------------------

CREATE INDEX idx_vendor_phone
ON vendors(phone_number);

CREATE INDEX idx_vendor_email
ON vendors(email);

CREATE INDEX idx_warehouse_vendor
ON warehouses(vendor_id);

CREATE INDEX idx_warehouse_city
ON warehouses(city);

CREATE INDEX idx_warehouse_pincode
ON warehouses(pincode);