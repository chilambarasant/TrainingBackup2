package com.agrieasy.pricing.dto;

public class CommodityMasterDTO2 {

	private int commodityId;
	private String commodityName;
	private String locationName;
	private String units;
	private String userName;
	private Float price;

	public CommodityMasterDTO2() {
	}

	public CommodityMasterDTO2(int commodityId, String commodityName, String locationName, String units,
			String userName, Float price) {
		this.commodityId = commodityId;
		this.commodityName = commodityName;
		this.locationName = locationName;
		this.units = units;
		this.userName = userName;
		this.price = price;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "CommodityMasterDTO [commodityId=" + commodityId + ", commodityName=" + commodityName + ", locationName="
				+ locationName + ", units=" + units + ", userName=" + userName + ", price=" + price + "]";
	}

}
