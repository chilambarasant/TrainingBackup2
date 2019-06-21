package com.agrieasy.uam.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agrieasy.uam.api.vo.SecurityQuestionsVo;
import com.agrieasy.uam.api.vo.UserSecurityQuestionVo;
import com.agrieasy.uam.api.vo.UserTypeVo;
import com.agrieasy.uam.api.vo.UserVo;
import com.agrieasy.uam.modal.SecurityQuestions;
import com.agrieasy.uam.modal.User;
import com.agrieasy.uam.modal.UserSecurityQuestion;
import com.agrieasy.uam.modal.UserType;

@Service
public class UserConverter {

	public User convert(UserVo vo) {
		
		User userData = new User();
		userData.setFirstName(vo.getFirstName());
		//userData.setId(vo.getId());
		userData.setLastName(vo.getLastName());
		userData.setLoginId(vo.getLoginId());
		userData.setMobileNo(vo.getMobileNo());
		userData.setPassword(vo.getPassword());
		userData.setUser_type(vo.getUser_type());
		if (vo.getUserSecurityQuestion() != null) {
			vo.getUserSecurityQuestion().forEach(i -> userData.addQuestion(convert(i)));
		}
		System.out.println("userData : "+ userData);
		return userData;
	}

	public UserSecurityQuestion convert(UserSecurityQuestionVo vo) {
		UserSecurityQuestion q = new UserSecurityQuestion();
		q.setAnswer(vo.getAnswer());
		q.setSecurityQuestionsId(vo.getSecurityQuestionsId());
		//q.setId(vo.getId());
		return q;
	}

	public List<UserTypeVo> convert(List<UserType> entity) {
		List<UserTypeVo> list = new ArrayList<>();
		entity.forEach(i -> list.add(new UserTypeVo(i.getId(), i.getUser_type())));
		return list;
	}

	public List<SecurityQuestionsVo> converter(List<SecurityQuestions> questions) {
		List<SecurityQuestionsVo> list = new ArrayList<>();
		questions.forEach(i -> list.add(new SecurityQuestionsVo(i.getId(), i.getQuestion())));
		return list;
	}
}
