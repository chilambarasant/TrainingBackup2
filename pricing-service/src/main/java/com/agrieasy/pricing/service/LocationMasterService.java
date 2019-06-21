package com.agrieasy.pricing.service;

import java.util.List;

import com.agrieasy.pricing.model.LocationMaster;

/**
 * @author sumilon.mondal
 *
 */
public interface LocationMasterService {

	/**
	 * @return LocationMaster Object in List
	 */
	public List<LocationMaster> getAllLocationMasterList();

	/**
	 * @param lmId
	 * @return Location Name
	 */
	public String getLocationMasterById(Integer lmId);
}
