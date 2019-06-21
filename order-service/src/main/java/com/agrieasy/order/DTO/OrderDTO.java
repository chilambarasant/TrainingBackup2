package com.agrieasy.order.DTO;

/**
 * @author sumilon.mondal
 *
 */
public class OrderDTO {

	private Integer ordId;
	private String cpCmName;
	private String cpLmName;
	private String cpUnits;
	private String cpAddress;
	private String ordDate;
	private String deliveryDate;
	private String orderStatus;

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public String getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(String ordDate) {
		this.ordDate = ordDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCpCmName() {
		return cpCmName;
	}

	public void setCpCmName(String cpCmName) {
		this.cpCmName = cpCmName;
	}

	public String getCpLmName() {
		return cpLmName;
	}

	public void setCpLmName(String cpLmName) {
		this.cpLmName = cpLmName;
	}

	public String getCpUnits() {
		return cpUnits;
	}

	public void setCpUnits(String cpUnits) {
		this.cpUnits = cpUnits;
	}

	public String getCpAddress() {
		return cpAddress;
	}

	public void setCpAddress(String cpAddress) {
		this.cpAddress = cpAddress;
	}

}
