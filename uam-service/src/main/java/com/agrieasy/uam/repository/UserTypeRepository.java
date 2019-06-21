package com.agrieasy.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrieasy.uam.modal.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, String> {

}
