package com.agrieasy.pricing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrieasy.pricing.model.LocationMaster;

/**
 * @author sumilon.mondal
 *
 */
public interface LocationMasterRepository extends JpaRepository<LocationMaster, Integer> {

	/**
	 * @param lmId
	 * @return LocationMaster Object
	 */
	public LocationMaster findByLmId(Integer lmId);
}
