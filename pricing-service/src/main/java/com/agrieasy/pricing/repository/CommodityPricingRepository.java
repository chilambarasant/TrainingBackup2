package com.agrieasy.pricing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agrieasy.pricing.dto.CommodityMasterDTO;
import com.agrieasy.pricing.model.CommodityPricing;

/**
 * @author sumilon.mondal
 *
 */
public interface CommodityPricingRepository extends JpaRepository<CommodityPricing, Integer> {

	/**
	 * @param cpCmId
	 * @return CommodityPricing Object in List
	 */
	public List<CommodityPricing> findByCpCmId(Integer cpCmId);

	/**
	 * @param cpLmId
	 * @return CommodityPricing Object in List
	 */
	public List<CommodityPricing> findByCpLmId(Integer cpLmId);

	/**
	 * @param cpLessPrice
	 * @param cpGreaterPrice
	 * @return CommodityPricing Object in List
	 */
	public List<CommodityPricing> findByCpPriceGreaterThanEqualAndCpPriceLessThanEqual(Float cpLessPrice,
			Float cpGreaterPrice);

	/**
	 * @param cpCmId
	 * @param cpLmId
	 * @return CommodityPricing Object in List
	 */
	public List<CommodityPricing> findByCpCmIdOrCpLmId(Integer cpCmId, Integer cpLmId);

	/**
	 * @param cpId
	 * @return CommodityPricing Object
	 */
	public CommodityPricing findBycpId(Integer cpId);

	/*@Query("select (select cmName from commodityMaster where cm_id=cp_cm_id),"
			+ "(select lm_name from location_master where lm_id=cp_lm_id), cp_userid, cp_price "
			+ "from commodity_pricing")
	public List<CommodityMasterDTO> getCommodityDetails();*/

}
