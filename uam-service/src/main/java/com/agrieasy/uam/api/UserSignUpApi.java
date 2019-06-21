package com.agrieasy.uam.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrieasy.uam.api.vo.SecurityQuestionsVo;
import com.agrieasy.uam.api.vo.UserVo;
import com.agrieasy.uam.exception.InvalidMobileNoException;
import com.agrieasy.uam.exception.InvalidUsercredential;
import com.agrieasy.uam.exception.UserNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/agri-easy")

public interface UserSignUpApi {

	@PostMapping("/sign-up")
	@ApiOperation("This Endpoint is used for signup Activity .")
	public ResponseEntity<String> userSignUp(@RequestBody @Valid UserVo userDate);
	
	@GetMapping("/get-all-user")	
	@ApiOperation("This Endpoint is used to Get UserDetails.")
	public ResponseEntity<String> getUserDate() throws UserNotFoundException;
	
	@PostMapping("/auth")
	@ApiOperation("This Endpoint is used to UserLogin.")
	public ResponseEntity<String> userLogin(@RequestBody String user) throws InvalidUsercredential;
	
	@GetMapping("/forgot-password/{phoneNo}")
	@ApiOperation("This Endpoint is used to RestPassword.")
	public ResponseEntity<String> checkForgetPassword(@RequestBody String phoneNo) throws InvalidMobileNoException;
	
	@PutMapping("/update-password")
	@ApiOperation("This Endpoint is used to UpdatePassowrd.")
	public ResponseEntity<String> updatePassword(@RequestBody UserVo vo) throws Exception;
	
	@Cacheable(value="security_questions")
	@GetMapping("/get-security-questions")
	@ApiOperation("This Endpoint is used to Get masterData of Security Questions.")
	public ResponseEntity<List<SecurityQuestionsVo>> getSecurityQuestions() throws Exception;
	
	@Cacheable(value="user_types")
	@GetMapping("/get-user-types")
	@ApiOperation("This Endpoint is used to Get masterData of User Types.")
	public ResponseEntity<String> getUserTypes() throws Exception;
	
	
	@GetMapping("/get-user-name/{userID}")
	@Cacheable(value="user_name")
	@ApiOperation("This Endpoint is used to Get masterData of User Types.")
	public ResponseEntity<String> getUserName(@PathVariable Long userID) throws Exception;
	
	@GetMapping("/check-user/{loginId}")
	@ApiOperation("This Endpoint is used to Get masterData of User Types.")
	public ResponseEntity<Boolean> checkUserId(@PathVariable Long loginId) throws Exception;
}
