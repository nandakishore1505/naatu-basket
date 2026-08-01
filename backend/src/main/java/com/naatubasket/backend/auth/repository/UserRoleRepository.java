package com.naatubasket.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naatubasket.backend.auth.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}