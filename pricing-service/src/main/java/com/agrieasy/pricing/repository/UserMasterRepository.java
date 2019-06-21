package com.agrieasy.pricing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrieasy.pricing.model.UserMaster;

public interface UserMasterRepository extends JpaRepository<UserMaster, Integer> {

	Optional<UserMaster> findByUmNameAndUmPassword(String username, String password);
}
