package com.agrieasy.pricing.dto;

import java.io.Serializable;

public class UserDTO {

	private int login_id;
	private String user_id;
	private String username;

	public UserDTO() {
	}

	public UserDTO(int login_id, String username) {
		this.login_id = login_id;
		this.username = username;
	}

	public UserDTO(int login_id, String user_id, String username) {
		this.login_id = login_id;
		this.user_id = user_id;
		this.username = username;
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserDTO [login_id=" + login_id + ", user_id=" + user_id + ", username=" + username + "]";
	}

}
