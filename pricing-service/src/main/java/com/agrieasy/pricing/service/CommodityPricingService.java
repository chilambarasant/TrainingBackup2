package com.agrieasy.pricing.service;

import java.util.List;

import com.agrieasy.pricing.dto.CommodityMasterDTO;
import com.agrieasy.pricing.exception.CommodityPricingNotFound;
import com.agrieasy.pricing.model.CommodityPricing;

/**
 * @author sumilon.mondal
 *
 */
public interface CommodityPricingService {

	/**
	 * @param commodityPricing
	 * @return String
	 */
	public String saveCommodity(CommodityPricing commodityPricing);

	/**
	 * @return CommodityPricing Object in List
	 * @throws CommodityPricingNotFound
	 */
	public List<CommodityPricing> getAllCommodityPricingList() throws CommodityPricingNotFound;

	/**
	 * @param cpCmId
	 * @return CommodityPricing Object in List
	 * @throws CommodityPricingNotFound
	 */
	public List<CommodityPricing> getByCpCmId(Integer cpCmId) throws CommodityPricingNotFound;

	/**
	 * @param cpLmId
	 * @return CommodityPricing Object in List
	 * @throws CommodityPricingNotFound
	 */
	public List<CommodityPricing> getByCpLmId(Integer cpLmId) throws CommodityPricingNotFound;

	/**
	 * @param cpLessPrice
	 * @param cpGreaterPrice
	 * @return CommodityPricing Object in List
	 * @throws CommodityPricingNotFound
	 */
	public List<CommodityPricing> getByCpPriceGreaterThanEqualAndCpPriceLessThanEqual(Float cpLessPrice,
			Float cpGreaterPrice) throws CommodityPricingNotFound;

	/**
	 * @param cpCmId
	 * @param cpLmId
	 * @return CommodityPricing Object in List
	 * @throws CommodityPricingNotFound
	 */
	public List<CommodityPricing> getByCpCmIdOrCpLmId(Integer cpCmId, Integer cpLmId) throws CommodityPricingNotFound;

	/**
	 * @param cpId
	 * @return True / False 
	 */
	public Boolean checkCommodityPricingId(Integer cpId);
	
	/**
	 * @param cpId
	 * @return CommodityPricing Object
	 */
	public CommodityPricing getCommodityDetailsById(Integer cpId);
	
	public List<CommodityMasterDTO> getCommodityDetails();
}
