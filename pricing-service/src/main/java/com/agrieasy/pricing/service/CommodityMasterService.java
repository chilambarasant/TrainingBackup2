package com.agrieasy.pricing.service;

import java.util.List;

import com.agrieasy.pricing.model.CommodityMaster;

/**
 * @author sumilon.mondal
 *
 */
public interface CommodityMasterService {

	/**
	 * @return CommodityMaster Object in List
	 */
	public List<CommodityMaster> getAllCommodityMasterList();

	/**
	 * @param cmId
	 * @return String Commodity name
	 */
	public String getCommodityMasterById(Integer cmId);

}
