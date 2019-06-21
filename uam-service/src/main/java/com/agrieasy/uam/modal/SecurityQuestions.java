package com.agrieasy.uam.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityQuestions {
	
	@Id
	private Integer id;
	private String question;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
