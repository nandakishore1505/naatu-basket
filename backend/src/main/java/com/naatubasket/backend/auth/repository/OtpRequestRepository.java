package com.naatubasket.backend.auth.repository;

import com.naatubasket.backend.auth.entity.OtpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtpRequestRepository extends JpaRepository<OtpRequest, Long> {

    List<OtpRequest> findByPhoneNumberOrderByCreatedAtDesc(String phoneNumber);

}