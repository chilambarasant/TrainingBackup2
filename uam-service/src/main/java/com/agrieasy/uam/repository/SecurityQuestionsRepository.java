package com.agrieasy.uam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrieasy.uam.modal.SecurityQuestions;

@Repository
public interface SecurityQuestionsRepository extends JpaRepository<SecurityQuestions, Integer>{

}
