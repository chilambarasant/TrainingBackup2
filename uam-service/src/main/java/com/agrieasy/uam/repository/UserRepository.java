package com.agrieasy.uam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrieasy.uam.modal.User;
import com.agrieasy.uam.modal.UserName;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLoginIdAndPassword(String userId, String passord);

	Optional<User> findByMobileNo(String phoneNo);
	
	Optional<UserName> findOneById(Long id);
}
