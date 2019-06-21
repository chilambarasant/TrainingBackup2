package com.agrieasy.pricing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author sumilon.mondal
 *
 */
@Entity
public class CommodityMaster {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cm_id")
	private Integer cmId;
	@Column(name = "cm_name")
	private String cmName;
	@Column(name = "cm_type")
	private String cmType;
	@Column(name = "cm_image")
	private String cmImage;

	public Integer getCmId() {
		return cmId;
	}

	public void setCmId(Integer cmId) {
		this.cmId = cmId;
	}

	public String getCmName() {
		return cmName;
	}

	public void setCmName(String cmName) {
		this.cmName = cmName;
	}

	public String getCmType() {
		return cmType;
	}

	public void setCmType(String cmType) {
		this.cmType = cmType;
	}

	public String getCmImage() {
		return cmImage;
	}

	public void setCmImage(String cmImage) {
		this.cmImage = cmImage;
	}

}
