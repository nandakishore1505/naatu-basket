-- ==========================================================
-- Naatu Basket
-- Version : V2
-- Module  : Authentication
-- Description :
--      Creates
--          1. users
--          2. user_roles
--          3. otp_requests
-- ==========================================================

------------------------------------------------------------
-- USERS
------------------------------------------------------------

CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100),

    phone_number VARCHAR(15) NOT NULL UNIQUE,

    email VARCHAR(255) UNIQUE,

    password_hash VARCHAR(255),

    is_phone_verified BOOLEAN NOT NULL DEFAULT FALSE,

    is_email_verified BOOLEAN NOT NULL DEFAULT FALSE,

    account_status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP
);

------------------------------------------------------------
-- USER ROLES
------------------------------------------------------------

CREATE TABLE user_roles
(
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,

    role_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id),

    CONSTRAINT uk_user_role
        UNIQUE(user_id, role_id)
);

------------------------------------------------------------
-- OTP REQUESTS
------------------------------------------------------------

CREATE TABLE otp_requests
(
    id BIGSERIAL PRIMARY KEY,

    phone_number VARCHAR(15) NOT NULL,

    otp_code VARCHAR(10) NOT NULL,

    expires_at TIMESTAMP NOT NULL,

    verified BOOLEAN NOT NULL DEFAULT FALSE,

    attempts INTEGER NOT NULL DEFAULT 0,

    ip_address VARCHAR(100),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP
);

------------------------------------------------------------
-- INDEXES
------------------------------------------------------------

CREATE INDEX idx_users_phone
ON users(phone_number);

CREATE INDEX idx_users_email
ON users(email);

CREATE INDEX idx_user_roles_user
ON user_roles(user_id);

CREATE INDEX idx_user_roles_role
ON user_roles(role_id);

CREATE INDEX idx_otp_phone
ON otp_requests(phone_number);