package com.agrieasy.pricing.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agrieasy.pricing.model.CommodityMaster;
import com.agrieasy.pricing.repository.CommodityMasterRepository;
import com.agrieasy.pricing.service.CommodityMasterService;

/**
 * @author sumilon.mondal
 *
 */
@Service
public class CommodityMasterServiceImpl implements CommodityMasterService {

	@Autowired
	private CommodityMasterRepository commodityMasterRepository;

	@Override
	public List<CommodityMaster> getAllCommodityMasterList() {
		return commodityMasterRepository.findAll();
	}

	@Override
	public String getCommodityMasterById(Integer cmId) {

		CommodityMaster commodityMaster = commodityMasterRepository.findByCmId(cmId);
		return commodityMaster.getCmName();
	}

}
