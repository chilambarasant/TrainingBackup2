package com.agrieasy.uam.modal;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Reference;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author manivel.perumal
 *
 */

@Entity
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Login ID is mandateory")
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

	@NotNull
	@Size(min = 10, max = 10, message = "Mobile No Should be 10 Numbers")
	@Pattern(regexp = "(^$|[0-9]{10})")
	@ApiModelProperty("Mobile No Is Mandatory and Should be 10 Numbers")
	private String mobileNo;

	@NotNull
	@Size(min = 5, max = 15, message = "Password Should be between 5 and 15 characters")
	@ApiModelProperty("Password Is Mandatory and Password Should be between 5 and 15 characters")
	private String password;

	private Date createdOn = new Date(new java.util.Date().getTime());

	@Reference
	private String user_type;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserSecurityQuestion> userSecurityQuestion =  new ArrayList<>();

	public List<UserSecurityQuestion> getUserSecurityQuestion() {
		return userSecurityQuestion;
	}

	public void setUserSecurityQuestion(List<UserSecurityQuestion> userSecurityQuestion) {
		this.userSecurityQuestion = userSecurityQuestion;
	}

	public void addQuestion(UserSecurityQuestion secQuestion) {
		if (!this.userSecurityQuestion.contains(secQuestion)) {
			System.out.println("inside add question !");
			secQuestion.setUser(this);
			this.userSecurityQuestion.add(secQuestion);
		}
		System.out.println("UserObj : " + this.getUserSecurityQuestion());
	}

	public Date getCreatedOn() {
		java.util.Date uDate = new java.util.Date();
		return new Date(uDate.getTime());
	}

	public void setCreatedOn(Date createdOn) {
		java.util.Date uDate = new java.util.Date();
		this.createdOn = new Date(uDate.getTime());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", loginId=" + loginId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNo=" + mobileNo + ", password=" + password + ", createdOn=" + createdOn + ", user_type="
				+ user_type + ", userSecurityQuestion=" + userSecurityQuestion + "]";
	}
	
}
