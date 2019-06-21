package com.agrieasy.uam.modal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserType {
	
	@Id
	private String user_type;
	private String id;
	
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
