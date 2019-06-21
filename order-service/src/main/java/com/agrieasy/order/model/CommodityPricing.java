package com.agrieasy.order.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author sumilon.mondal
 *
 */
@Entity
public class CommodityPricing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "CommodityPricing Id - Auto Generated")
	@Column(name = "cp_id")
	private Integer cpId;

	@ApiModelProperty(notes = "CommodityId of the product")
	@NotNull(message = "Commodity cannot be empty")
	@Column(name = "cp_cm_id")
	private Integer cpCmId;

	@ApiModelProperty(notes = "UserId of the customer")
	@NotNull(message = "User cannot be empty")
	@Column(name = "cp_userid")
	private Integer cpUserId;

	@ApiModelProperty(notes = "LocationId of the place")
	@NotNull(message = "Location cannot be empty")
	@Column(name = "cp_lm_id")
	private Integer cpLmId;

	@ApiModelProperty(notes = "Price of the commodity")
	@NotNull(message = "Price cannot be empty")
	@Column(name = "cp_price")
	private Float cpPrice;

	@ApiModelProperty(notes = "Units of the commodity")
	@NotNull(message = "Units cannot be empty")
	@Column(name = "cp_units")
	private String cpUnits;

	@ApiModelProperty(notes = "Status")
	@Column(name = "cp_status")
	private Integer cpStatus;

	@ApiModelProperty(notes = "Commodity Createdby ID")
	@Column(name = "cp_createdby")
	private Integer cpCreatedby;

	@ApiModelProperty(notes = "Commodity Created On")
	@Column(name = "cp_createdon")
	private Date cpCreatedon;

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public Integer getCpCmId() {
		return cpCmId;
	}

	public void setCpCmId(Integer cpCmId) {
		this.cpCmId = cpCmId;
	}

	public Integer getCpUserId() {
		return cpUserId;
	}

	public void setCpUserId(Integer cpUserId) {
		this.cpUserId = cpUserId;
	}

	public Integer getCpLmId() {
		return cpLmId;
	}

	public void setCpLmId(Integer cpLmId) {
		this.cpLmId = cpLmId;
	}

	public Float getCpPrice() {
		return cpPrice;
	}

	public void setCpPrice(Float cpPrice) {
		this.cpPrice = cpPrice;
	}

	public Integer getCpStatus() {
		return cpStatus;
	}

	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}

	public Integer getCpCreatedby() {
		return cpCreatedby;
	}

	public void setCpCreatedby(Integer cpCreatedby) {
		this.cpCreatedby = cpCreatedby;
	}

	public Date getCpCreatedon() {
		return cpCreatedon;
	}

	public void setCpCreatedon(Date cpCreatedon) {
		this.cpCreatedon = cpCreatedon;
	}

	public String getCpUnits() {
		return cpUnits;
	}

	public void setCpUnits(String cpUnits) {
		this.cpUnits = cpUnits;
	}

}
