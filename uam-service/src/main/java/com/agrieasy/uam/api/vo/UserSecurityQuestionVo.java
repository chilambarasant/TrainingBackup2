package com.agrieasy.uam.api.vo;

public class UserSecurityQuestionVo {
	
	private Integer id;
	private String securityQuestionsId;
	private String answer;
	
	public UserSecurityQuestionVo(Integer id, String securityQuestionsId, String answer) {
		super();
		this.id = id;
		this.securityQuestionsId = securityQuestionsId;
		this.answer = answer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSecurityQuestionsId() {
		return securityQuestionsId;
	}

	public void setSecurityQuestionsId(String securityQuestionsId) {
		this.securityQuestionsId = securityQuestionsId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", securityQuestionsId=" + securityQuestionsId + ", answer="
				+ answer + "]";
	}
	
}
