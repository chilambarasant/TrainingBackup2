package com.agrieasy.pricing.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.agrieasy.pricing.dto.CommodityMasterDTO;
import com.agrieasy.pricing.dto.UserDTO;
import com.agrieasy.pricing.exception.CommodityPricingNotFound;
import com.agrieasy.pricing.model.CommodityMaster;
import com.agrieasy.pricing.model.CommodityPricing;
import com.agrieasy.pricing.model.LocationMaster;
import com.agrieasy.pricing.repository.CommodityMasterRepository;
import com.agrieasy.pricing.repository.CommodityPricingRepository;
import com.agrieasy.pricing.repository.LocationMasterRepository;
import com.agrieasy.pricing.service.CommodityPricingService;
import com.agrieasy.pricing.utility.Utility;

/**
 * @author sumilon.mondal
 *
 */
@Service
public class CommodityPricingServiceImpl implements CommodityPricingService {

	@Autowired
	private CommodityPricingRepository commodityPricingRepository;

	@Autowired
	private CommodityMasterRepository commodityMasterRepository;

	@Autowired
	private LocationMasterRepository locationMasterRepository;

	@Autowired
	private Utility utility;

	@Override
	public String saveCommodity(CommodityPricing commodityPricing) {
		
		System.out.println("Inside service : "+commodityPricing);

		try {
			commodityPricingRepository.save(commodityPricing);
			return "Commodity Details saved successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Commodity Details insertion failure";
		}
	}

	@Override
	public List<CommodityPricing> getAllCommodityPricingList() throws CommodityPricingNotFound {

		List<CommodityPricing> commodityPricings = commodityPricingRepository.findAll();
		if (commodityPricings == null || commodityPricings.isEmpty()) {
			throw new CommodityPricingNotFound(utility.getMessageDescription("NOT_FOUND"));
		}
		return commodityPricings;
	}

	@Override
	public List<CommodityPricing> getByCpCmId(Integer cpCmId) throws CommodityPricingNotFound {
		List<CommodityPricing> commodityPricings = commodityPricingRepository.findByCpCmId(cpCmId);
		if (commodityPricings == null || commodityPricings.isEmpty()) {
			throw new CommodityPricingNotFound(utility.getMessageDescription("NOT_FOUND"));
		}
		return commodityPricings;
	}

	@Override
	public List<CommodityPricing> getByCpLmId(Integer cpLmId) throws CommodityPricingNotFound {
		List<CommodityPricing> commodityPricings = commodityPricingRepository.findByCpLmId(cpLmId);
		if (commodityPricings == null || commodityPricings.isEmpty()) {
			throw new CommodityPricingNotFound(utility.getMessageDescription("NOT_FOUND"));
		}
		return commodityPricings;
	}

	@Override
	public List<CommodityPricing> getByCpPriceGreaterThanEqualAndCpPriceLessThanEqual(Float cpLessPrice,
			Float cpGreaterPrice) throws CommodityPricingNotFound {
		List<CommodityPricing> commodityPricings = commodityPricingRepository
				.findByCpPriceGreaterThanEqualAndCpPriceLessThanEqual(cpLessPrice, cpGreaterPrice);
		if (commodityPricings == null || commodityPricings.isEmpty()) {
			throw new CommodityPricingNotFound(utility.getMessageDescription("NOT_FOUND"));
		}
		return commodityPricings;
	}

	@Override
	public List<CommodityPricing> getByCpCmIdOrCpLmId(Integer cpCmId, Integer cpLmId) throws CommodityPricingNotFound {

		List<CommodityPricing> commodityPricings = commodityPricingRepository.findByCpCmIdOrCpLmId(cpCmId, cpLmId);

		if (cpCmId == null && cpLmId == null || cpCmId == 0 && cpLmId == 0) {
			throw new CommodityPricingNotFound(utility.getMessageDescription("ATLEAST_ONE_VALUE"));
		} else {
			if (commodityPricings == null || commodityPricings.isEmpty()) {
				throw new CommodityPricingNotFound(utility.getMessageDescription("NOT_FOUND"));
			}
		}

		return commodityPricings;
	}

	@Override
	public Boolean checkCommodityPricingId(Integer cpId) {

		return commodityPricingRepository.existsById(cpId);
	}

	@Override
	public CommodityPricing getCommodityDetailsById(Integer cpId) {

		return commodityPricingRepository.findBycpId(cpId);
	}

	@Override
	public List<CommodityMasterDTO> getCommodityDetails() {

		RestTemplate restTemplate = new RestTemplate();
		List<UserDTO> userDTO = new ArrayList<>();

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Token", "********");
			System.out.println("***************************");
			System.out.println(headers.toString());
			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			ResponseEntity<List<UserDTO>> userResponse = restTemplate.exchange(
					"http://localhost:5050/agri-easy/get-all-user", HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<UserDTO>>() {
					});

			userDTO = userResponse.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			userDTO.add(new UserDTO(1, "Sumilon Mondal"));
			userDTO.add(new UserDTO(2, "Mani Vel"));
			userDTO.add(new UserDTO(3, "Suresh Palani"));
			userDTO.add(new UserDTO(4, "Naveen"));
			userDTO.add(new UserDTO(5, "Lohit"));
		}

		System.out.println(userDTO.toString());
		
		Map<Integer, String> userMap = new HashMap<>();

		for (UserDTO userDTO2 : userDTO) {
			userMap.put(userDTO2.getLogin_id(), userDTO2.getUsername());
		}

		List<CommodityMaster> commodityMasters = commodityMasterRepository.findAll();

		Map<Integer, String> commodityMap = new HashMap<>();

		for (CommodityMaster commodityMaster : commodityMasters) {
			commodityMap.put(commodityMaster.getCmId(), commodityMaster.getCmName()+"#####"+commodityMaster.getCmImage());
		}

		List<LocationMaster> locationMasters = locationMasterRepository.findAll();

		Map<Integer, String> locationMap = new HashMap<>();

		for (LocationMaster locationMaster : locationMasters) {
			locationMap.put(locationMaster.getLmId(), locationMaster.getLmName());
		}

		List<CommodityPricing> commodityPricings = commodityPricingRepository.findAll();
		
		commodityPricings = commodityPricings.stream().filter(e->e.getCpStatus()==1).collect(Collectors.toList());

		List<CommodityMasterDTO> commodityMasterDTOs = new ArrayList<>();

		for (CommodityPricing commodityPricing : commodityPricings) {
			CommodityMasterDTO dto = new CommodityMasterDTO();
			dto.setCommodityId(commodityPricing.getCpId());
			dto.setCommodityName(commodityMap.get(commodityPricing.getCpCmId()).split("#####")[0]);
			dto.setCommodityImage(commodityMap.get(commodityPricing.getCpCmId()).split("#####")[1]);
			dto.setLocationName(locationMap.get(commodityPricing.getCpLmId()));
			dto.setUnits(commodityPricing.getCpUnits());
			dto.setPrice(commodityPricing.getCpPrice());
			dto.setUserName(userMap.get(commodityPricing.getCpUserId()));
			commodityMasterDTOs.add(dto);
		}

		System.out.println(commodityMasterDTOs.toString());

		return commodityMasterDTOs;
	}

}
