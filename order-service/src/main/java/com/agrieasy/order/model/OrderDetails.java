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
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Order Id - Auto Generated")
	private Integer ordId;

	@ApiModelProperty(notes = "Order Date")
	@NotNull(message = "Order Date cannot be empty")
	private Date ordDate;

	@ApiModelProperty(notes = "Commodity Price Id")
	@NotNull(message = "Commodity Price ID cannot be empty")
	private Integer ordCpId;

	@ApiModelProperty(notes = "Order Delivery Date")
	@NotNull(message = "Order Delivery Date cannot be empty")
	private Date deliveryDate;

	@ApiModelProperty(notes = "Address of the commodity")
	@NotNull(message = "Address cannot be empty")
	@Column(name = "cp_address")
	private String cpAddress;

	@ApiModelProperty(notes = "Order Status")
	private String orderStatus;

	@ApiModelProperty(notes = "Order CreatedOn")
	private Date ordCreatedOn;

	@ApiModelProperty(notes = "Order CreatedBy")
	private Integer createdBy;

	public Integer getOrdId() {
		return ordId;
	}

	public void setOrdId(Integer ordId) {
		this.ordId = ordId;
	}

	public Date getOrdDate() {
		return ordDate;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public Integer getOrdCpId() {
		return ordCpId;
	}

	public void setOrdCpId(Integer ordCpId) {
		this.ordCpId = ordCpId;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrdCreatedOn() {
		return ordCreatedOn;
	}

	public void setOrdCreatedOn(Date ordCreatedOn) {
		this.ordCreatedOn = ordCreatedOn;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCpAddress() {
		return cpAddress;
	}

	public void setCpAddress(String cpAddress) {
		this.cpAddress = cpAddress;
	}

	@Override
	public String toString() {
		return "OrderDetails [ordId=" + ordId + ", ordDate=" + ordDate + ", ordCpId=" + ordCpId + ", deliveryDate="
				+ deliveryDate + ", cpAddress=" + cpAddress + ", orderStatus=" + orderStatus + ", ordCreatedOn="
				+ ordCreatedOn + ", createdBy=" + createdBy + "]";
	}

}
