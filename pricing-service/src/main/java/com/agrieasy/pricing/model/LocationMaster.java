package com.agrieasy.pricing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author sumilon.mondal
 *
 */
@Entity
public class LocationMaster {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lm_id")
	private Integer lmId;
	@Column(name = "lm_code")
	private String lmCode;
	@Column(name = "lm_name")
	private String lmName;

	public Integer getLmId() {
		return lmId;
	}

	public void setLmId(Integer lmId) {
		this.lmId = lmId;
	}

	public String getLmCode() {
		return lmCode;
	}

	public void setLmCode(String lmCode) {
		this.lmCode = lmCode;
	}

	public String getLmName() {
		return lmName;
	}

	public void setLmName(String lmName) {
		this.lmName = lmName;
	}

}
