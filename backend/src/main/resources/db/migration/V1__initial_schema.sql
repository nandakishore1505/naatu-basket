-- ==========================================================
-- Naatu Basket
-- Version : V1
-- Module  : Initial Schema
-- Description :
--      Creates roles table
-- ==========================================================

------------------------------------------------------------
-- ROLES
------------------------------------------------------------

CREATE TABLE roles
(
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(50) NOT NULL UNIQUE,

    description VARCHAR(255),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP
);

------------------------------------------------------------
-- INDEXES
------------------------------------------------------------

CREATE INDEX idx_roles_name
ON roles(name);

------------------------------------------------------------
-- DEFAULT ROLES
------------------------------------------------------------

INSERT INTO roles(name, description)
VALUES
('CUSTOMER', 'Customer using the platform'),
('ADMIN', 'System administrator'),
('VENDOR', 'Vendor supplying products'),
('DELIVERY_PARTNER', 'Delivery executive');