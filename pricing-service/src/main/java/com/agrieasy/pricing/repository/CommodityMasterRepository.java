package com.agrieasy.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrieasy.pricing.model.CommodityMaster;

/**
 * @author sumilon.mondal
 *
 */
public interface CommodityMasterRepository extends JpaRepository<CommodityMaster, Integer> {

	/**
	 * @param cmId - commodity Id
	 * @return CommodityMaster Object
	 */
	public CommodityMaster findByCmId(Integer cmId);
}
