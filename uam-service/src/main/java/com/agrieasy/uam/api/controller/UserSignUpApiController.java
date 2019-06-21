package com.agrieasy.uam.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.agrieasy.uam.api.UserSignUpApi;
import com.agrieasy.uam.api.vo.SecurityQuestionsVo;
import com.agrieasy.uam.api.vo.UserTypeVo;
import com.agrieasy.uam.api.vo.UserVo;
import com.agrieasy.uam.exception.InvalidMobileNoException;
import com.agrieasy.uam.exception.InvalidUsercredential;
import com.agrieasy.uam.exception.UserNotFoundException;
import com.agrieasy.uam.modal.User;
import com.agrieasy.uam.repository.impl.UAMRepositoryImpl;

@Service
public class UserSignUpApiController implements UserSignUpApi {
	
	Logger logger = LoggerFactory.getLogger(UserSignUpApiController.class);

	@Autowired
	private UAMRepositoryImpl reposervice;

	@Override
	public ResponseEntity<String> userSignUp(@RequestBody @Valid UserVo userData) {
		System.out.println(userData);
		reposervice.saveUserData(userData);
		return new ResponseEntity<>("User Details Successfully Saved !", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getUserDate() throws UserNotFoundException {
		JSONArray user = reposervice.getUserData();
		return new ResponseEntity<String>(user.toString(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> userLogin(@RequestBody String user) throws InvalidUsercredential {
		logger.info("User SignedIn ...!");
		logger.debug("User Logged IN - Debug");
		logger.error("User LoggedIn-error");
		JSONObject obj = new JSONObject(user);
		User userData = reposervice.usreLogin(obj.getString("loginId"), obj.getString("password"));
		JSONObject json = new JSONObject();
		json.put("userId",userData.getId());
		json.put("user_type",userData.getUser_type());
		json.put("firstName",userData.getFirstName());
		return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> checkForgetPassword(@PathVariable String phoneNo) throws InvalidMobileNoException {
		User user = reposervice.checkForgetPassword(phoneNo);
		return new ResponseEntity<String>(user.toString(), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> updatePassword(@RequestBody UserVo vo) throws Exception {
		reposervice.savePassword(vo);
		return new ResponseEntity<String>("Success !", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<SecurityQuestionsVo>> getSecurityQuestions() throws Exception {
		List<SecurityQuestionsVo> securityQuestions = reposervice.getSecurityQuestions();
		return new ResponseEntity<List<SecurityQuestionsVo>>(securityQuestions, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getUserTypes() throws Exception {
		List<UserTypeVo> userType = reposervice.getUserType();
		return new ResponseEntity<String>(userType.toString(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> getUserName(@PathVariable Long userID) throws Exception {
		System.out.println("getUserName : " + userID);
		String str = reposervice.getUserName(userID);
		return new ResponseEntity<String>(str, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> checkUserId(@PathVariable Long loginId) throws Exception {
		boolean existes = reposervice.checkUser(loginId);
		return new ResponseEntity<Boolean>(existes, HttpStatus.OK);
	}
}
