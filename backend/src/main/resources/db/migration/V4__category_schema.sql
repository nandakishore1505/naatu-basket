-- ==========================================================
-- Naatu Basket
-- Version : V4
-- Module  : Category
-- ==========================================================

------------------------------------------------------------
-- CATEGORIES
------------------------------------------------------------

CREATE TABLE categories
(
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    description TEXT,

    image_url VARCHAR(500),

    parent_category_id BIGINT,

    display_order INTEGER NOT NULL DEFAULT 0,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP,

    CONSTRAINT fk_category_parent
        FOREIGN KEY (parent_category_id)
        REFERENCES categories(id)
);

------------------------------------------------------------
-- INDEXES
------------------------------------------------------------

CREATE INDEX idx_category_name
ON categories(name);

CREATE INDEX idx_category_parent
ON categories(parent_category_id);

------------------------------------------------------------
-- SAMPLE DATA
------------------------------------------------------------

INSERT INTO categories(name, description, display_order)
VALUES
('Fruits', 'Fresh Fruits', 1),
('Vegetables', 'Fresh Vegetables', 2),
('Dairy', 'Milk and Dairy Products', 3),
('Bakery', 'Bread and Bakery Items', 4),
('Beverages', 'Cold Drinks and Juices', 5);