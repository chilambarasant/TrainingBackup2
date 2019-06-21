package com.agrieasy.pricing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author sumilon.mondal
 *
 */
@Entity
public class UserMaster {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "um_id")
	private Integer umId;
	@Column(name = "um_name")
	private String umName;
	@Column(name = "um_password")
	private String umPassword;

	public UserMaster(Integer umId, String umName, String umPassword) {
		this.umId = umId;
		this.umName = umName;
		this.umPassword = umPassword;
	}

	public Integer getUmId() {
		return umId;
	}

	public void setUmId(Integer umId) {
		this.umId = umId;
	}

	public String getUmName() {
		return umName;
	}

	public void setUmName(String umName) {
		this.umName = umName;
	}

	public String getUmPassword() {
		return umPassword;
	}

	public void setUmPassword(String umPassword) {
		this.umPassword = umPassword;
	}

	@Override
	public String toString() {
		return "UserMaster [umId=" + umId + ", umName=" + umName + ", umPassword=" + umPassword + "]";
	}

}
