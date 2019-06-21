package com.agrieasy.uam.api.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class UserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	@NotNull(message = "LOGIN_ID")
	@ApiModelProperty("LoginID Is Mandatory")
	private String loginId;

	@NotNull(message = "FirstName should not Be null")
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$", message = "Special chars not allowd")
	@ApiModelProperty("FirstName Should Have 15 char only.")
	private String firstName;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$", message = "Special chars not allowd")
	@ApiModelProperty("LastName Should Have 15 char only.")
	private String lastName;

	@NotNull(message="MOBILE_NO")
	@Size(min = 10, max = 10, message = "Mobile No Should be 10 Numbers")
	@Pattern(regexp = "(^$|[0-9]{10})")
	@ApiModelProperty("Mobile No Is Mandatory and Should be 10 Numbers")
	private String mobileNo;

	@NotNull
	@Size(min = 5, max = 15, message = "Password Should be between 5 and 15 characters")
	@ApiModelProperty("Password Is Mandatory and Password Should be between 5 and 15 characters")
	private String password;
	
	private String user_type;
	
	private List<UserSecurityQuestionVo> userSecurityQuestion;

	public List<UserSecurityQuestionVo> getUserSecurityQuestion() {
		return userSecurityQuestion;
	}

	public UserVo setUserSecurityQuestion(List<UserSecurityQuestionVo> userSecurityQuestion) {
		this.userSecurityQuestion = userSecurityQuestion;
		return this;
	}

	public long getId() {
		return id;
	}

	public UserVo setId(long id) {
		this.id = id;
		return this;
	}

	public String getLoginId() {
		return loginId;
	}

	public UserVo setLoginId(String loginId) {
		this.loginId = loginId;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserVo setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserVo setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public UserVo setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserVo setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
}
