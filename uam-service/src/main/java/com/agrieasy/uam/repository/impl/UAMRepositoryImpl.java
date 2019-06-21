package com.agrieasy.uam.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrieasy.uam.api.vo.SecurityQuestionsVo;
import com.agrieasy.uam.api.vo.UserTypeVo;
import com.agrieasy.uam.api.vo.UserVo;
import com.agrieasy.uam.converter.UserConverter;
import com.agrieasy.uam.exception.InvalidMobileNoException;
import com.agrieasy.uam.exception.InvalidUsercredential;
import com.agrieasy.uam.exception.UserNotFoundException;
import com.agrieasy.uam.modal.SecurityQuestions;
import com.agrieasy.uam.modal.User;
import com.agrieasy.uam.modal.UserName;
import com.agrieasy.uam.modal.UserSecurityQuestion;
import com.agrieasy.uam.modal.UserType;
import com.agrieasy.uam.repository.SecurityQuestionsRepository;
import com.agrieasy.uam.repository.UserRepository;
import com.agrieasy.uam.repository.UserTypeRepository;

@Service
public class UAMRepositoryImpl {

	@Autowired
	private UserRepository repository;

	@Autowired
	private SecurityQuestionsRepository securityQuestionsRepository;

	@Autowired
	private UserTypeRepository userTypeRepo;

	@Autowired
	private UserConverter converter;

	public User saveUserData(UserVo userData) {
		return repository.save(converter.convert(userData));
	}

	public void savePassword(UserVo userData) throws Exception {

		Optional<User> db = repository.findById(userData.getId());
		if (db.isPresent()) {
			User dbObj = db.get();
			dbObj.setPassword(userData.getPassword());
			repository.save(dbObj);
		} else {
			throw new Exception();
		}
	}

	public JSONArray getUserData() throws UserNotFoundException {
		List<User> user = repository.findAll();

		JSONArray arr = new JSONArray();
		for (User userData : user) {
			JSONObject obj = new JSONObject();
			obj.put("login_id", userData.getId());
			obj.put("user_id", userData.getLoginId());
			obj.put("username", userData.getFirstName() + " " + userData.getLastName());
			arr.put(obj);
		}
		return arr;
	}

	public User usreLogin(String userId, String passord) throws InvalidUsercredential {
		Optional<User> user = repository.findByLoginIdAndPassword(userId, passord);
		return user.orElseThrow(() -> new InvalidUsercredential("INVALID_CREDENTIALS"));
	}

	public User checkForgetPassword(String mobileNo) throws InvalidMobileNoException {
		Optional<User> user = repository.findByMobileNo(mobileNo);

		if (user.isPresent()) {
			List<UserSecurityQuestion> secQuestion = user.get().getUserSecurityQuestion();
			Random rand = new Random();
			System.out.println("Security Size :: " + secQuestion.size());
			int value = rand.nextInt(secQuestion.size());
			List<UserSecurityQuestion> secList = new ArrayList<UserSecurityQuestion>();
			secList.add(secQuestion.get(value));
			User u = user.get();
			u.setUserSecurityQuestion(secList);
			return u;
		} else {
			throw new InvalidMobileNoException("INVALID_MOBILE_NO");
		}
	}

	public List<UserTypeVo> getUserType() {

		List<UserType> user = userTypeRepo.findAll();
		List<UserTypeVo> list = converter.convert(user);
		return list;

	}

	public List<SecurityQuestionsVo> getSecurityQuestions() {
		List<SecurityQuestions> questions = securityQuestionsRepository.findAll();
		List<SecurityQuestionsVo> list = converter.converter(questions);
		return list;
	}

	public String getUserName(Long loginId) throws Exception {
		Optional<UserName> data = repository.findOneById(loginId);
		if (data.isPresent()) {
			UserName u = data.get();
			String uname = u.getFirstName() + " " + u.getLastName();
			return uname;
		} else {
			throw new Exception();
		}
	}

	public boolean checkUser(Long userId) {
		Optional<UserName> user = repository.findOneById(userId);
		return user.isPresent() ? true : false;
	}

	/*
	 * public ResponseEntity<String> editUserDate(UserVo userData) throws
	 * UserNotFoundException { Optional<User> userRef =
	 * repository.findById(userData.getId());
	 * 
	 * User user = userRef.get(); user.setFirstName(user.getFirstName()); /////
	 * userData.getUserSecurityQuestion().forEach(i -> { UserSecurityQuestion q =
	 * converter.convert(i); user.addQuestion(q); }); return new
	 * ResponseEntity<String>(user.toString(), HttpStatus.OK); }
	 */

}
