package com.agrieasy.uam.api.vo;

public class UserTypeVo {

	private String user_type;
	private String id;

	public UserTypeVo(String user_type, String id) {
		this.user_type = user_type;
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "[user_type=" + user_type + ", id=" + id + "]";
	}
}
