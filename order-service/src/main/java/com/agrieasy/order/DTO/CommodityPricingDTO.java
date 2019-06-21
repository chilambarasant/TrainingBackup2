package com.agrieasy.order.DTO;

import java.util.Date;

/**
 * @author sumilon.mondal
 *
 */
public class CommodityPricingDTO {

	private Integer cpId;
	private Integer cpCmId;
	private Integer cpUserId;
	private Integer cpLmId;
	private Float cpPrice;
	private Integer cpCreatedby;
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

	@Override
	public String toString() {
		return "CommodityPricingDTO [cpId=" + cpId + ", cpCmId=" + cpCmId + ", cpUserId=" + cpUserId + ", cpLmId="
				+ cpLmId + ", cpPrice=" + cpPrice + ", cpCreatedby=" + cpCreatedby + ", cpCreatedon=" + cpCreatedon
				+ "]";
	}

}
