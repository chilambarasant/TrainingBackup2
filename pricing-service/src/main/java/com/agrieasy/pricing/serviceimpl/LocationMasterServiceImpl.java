package com.agrieasy.pricing.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrieasy.pricing.model.LocationMaster;
import com.agrieasy.pricing.repository.LocationMasterRepository;
import com.agrieasy.pricing.service.LocationMasterService;

/**
 * @author sumilon.mondal
 *
 */
@Service
public class LocationMasterServiceImpl implements LocationMasterService {

	@Autowired
	private LocationMasterRepository locationMasterRepository;

	@Override
	public List<LocationMaster> getAllLocationMasterList() {
		return locationMasterRepository.findAll();
	}

	@Override
	public String getLocationMasterById(Integer lmId) {

		LocationMaster locationMaster = locationMasterRepository.findByLmId(lmId);
		return locationMaster.getLmName();
	}
}
