package com.agrieasy.pricing.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agrieasy.pricing.dto.CommodityMasterDTO;
import com.agrieasy.pricing.dto.CommodityMasterDTO2;
import com.agrieasy.pricing.exception.CommodityPricingNotFound;
import com.agrieasy.pricing.model.CommodityMaster;
import com.agrieasy.pricing.model.CommodityPricing;
import com.agrieasy.pricing.model.LocationMaster;
import com.agrieasy.pricing.service.CommodityMasterService;
import com.agrieasy.pricing.service.CommodityPricingService;
import com.agrieasy.pricing.service.LocationMasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author sumilon.mondal
 *
 */
@RestController
@RequestMapping("/v1")
@Api(value = "pricing-service", description = "All Operations for Commodity Pricing")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommodityController {

	Logger logger = LoggerFactory.getLogger(CommodityController.class);

	@Autowired
	private CommodityPricingService commodityService;

	@Autowired
	private CommodityMasterService commodityMasterService;

	@Autowired
	private LocationMasterService locationMasterService;

	/**
	 * @param commodityPricing
	 * @return Success / Failure Message
	 */
	@ApiOperation(value = "Save Commodity Pricing Details")
	@PostMapping("/save-commodity")
	public ResponseEntity<String> saveCommodity(@Valid @RequestBody CommodityPricing commodityPricing) {

		try {
			CommodityPricing newCommodityPricing = commodityPricing;
			newCommodityPricing.setCpCreatedon(new Date());
			newCommodityPricing.setCpStatus(1);

			System.out.println("inside controller : " + commodityPricing);

			return Optional.ofNullable(commodityService.saveCommodity(newCommodityPricing))
					.map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
					.orElseThrow(() -> new RuntimeException("Commodity creation failed"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @return CommodityPricing Object in List
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get List of Commodity Pricing Details")
	@GetMapping("/get-commodity")
	public ResponseEntity<List<CommodityPricing>> getCommodityDetails()
			throws RuntimeException, CommodityPricingNotFound {

		return Optional.ofNullable(commodityService.getAllCommodityPricingList())
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity details"));
	}

	/**
	 * @param cpId - commodity pricing ID
	 * @return CommodityPricing Object
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get Commodity Pricing Details based on commodityId")
	@GetMapping("/get-commodity/{cpId}")
	public ResponseEntity<CommodityPricing> getCommodityDetailsById(@PathVariable("cpId") Integer cpId)
			throws RuntimeException, CommodityPricingNotFound {

		return Optional.ofNullable(commodityService.getCommodityDetailsById(cpId))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity details"));
	}

	/**
	 * @param cpCmId
	 * @return CommodityPricing Object in List
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get List of Pricing based on selected commodity")
	@PostMapping("/get-pricing-basedon-commodity/{cpCmId}")
	public ResponseEntity<List<CommodityPricing>> getPricingBasedOnCommodity(@PathVariable("cpCmId") Integer cpCmId)
			throws RuntimeException, CommodityPricingNotFound {

		return Optional.ofNullable(commodityService.getByCpCmId(cpCmId))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity details"));
	}

	/**
	 * @param cpLmId
	 * @return CommodityPricing Object in List
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get List of Pricing based on selected location")
	@PostMapping("/get-pricing-basedon-location/{cpLmId}")
	public ResponseEntity<List<CommodityPricing>> getPricingBasedOnLocation(@PathVariable("cpLmId") Integer cpLmId)
			throws RuntimeException, CommodityPricingNotFound {

		return Optional.ofNullable(commodityService.getByCpLmId(cpLmId))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity details"));
	}

	/**
	 * @param cpLessPrice    - Minimum Price
	 * @param cpGreaterPrice - Maximum Price
	 * @return CommodityPricing Object in List
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get List of Pricing based on selected min and max pricing")
	@PostMapping("/get-commodity-basedon-min_and-max-price/{cpLessPrice}/{cpGreaterPrice}")
	public ResponseEntity<List<CommodityPricing>> getCommodityBasedOnMinAndMaxPrice(
			@PathVariable("cpLessPrice") Float cpLessPrice, @PathVariable("cpGreaterPrice") Float cpGreaterPrice)
			throws RuntimeException, CommodityPricingNotFound {

		return Optional
				.ofNullable(commodityService.getByCpPriceGreaterThanEqualAndCpPriceLessThanEqual(cpLessPrice,
						cpGreaterPrice))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity details"));
	}

	/**
	 * @param cpCmId - commodity Id
	 * @param cpLmId - location Id
	 * @return CommodityPricing Object in List
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get List of Pricing based on selected commodity or location")
	@PostMapping("/get-pricing-basedon-commodity-or-location")
	public ResponseEntity<List<CommodityPricing>> getCommodityBasedOnMinAndMaxPrice(
			@RequestParam(required = false, name = "cpCmId") Integer cpCmId,
			@RequestParam(required = false, name = "cpLmId") Integer cpLmId)
			throws RuntimeException, CommodityPricingNotFound {

		return Optional.ofNullable(commodityService.getByCpCmIdOrCpLmId(cpCmId, cpLmId))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity details"));
	}

	/**
	 * @param cpId - Commodity Id
	 * @return String Commodity Pricing is present or not
	 */
	@GetMapping("/check-commodity-pricing/{cpId}")
	public ResponseEntity<String> checkCommodityPricingId(@PathVariable("cpId") Integer cpId) {

		return Optional.ofNullable(commodityService.checkCommodityPricingId(cpId) ? "Available" : "Not Available")
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity details"));
	}

	/**
	 * @return Commodity Master Object in List
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get List of Commodity Master Details")
	@Cacheable(value = "get-commodity-master")
	@GetMapping("/get-commodity-master")
	public ResponseEntity<List<CommodityMaster>> getCommodityMaster()
			throws RuntimeException, CommodityPricingNotFound {

		return Optional.ofNullable(commodityMasterService.getAllCommodityMasterList())
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity master details"));
	}

	/**
	 * @return Location Master Object in List
	 * @throws RuntimeException
	 * @throws CommodityPricingNotFound
	 */
	@ApiOperation(value = "Get List of Location Master Details")
	@Cacheable(value = "get-location-master")
	@GetMapping("/get-location-master")
	public ResponseEntity<List<LocationMaster>> getLocationMaster() throws RuntimeException, CommodityPricingNotFound {

		return Optional.ofNullable(locationMasterService.getAllLocationMasterList())
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching location master details"));
	}

	/**
	 * @param cmId
	 * @return Commodity Name
	 */
	@ApiOperation(value = "Get Commodity name based on ID")
	@GetMapping("/get-commodity-name/{cmId}")
	public ResponseEntity<String> getCommodityById(@PathVariable("cmId") Integer cmId) {
		return Optional.ofNullable(commodityMasterService.getCommodityMasterById(cmId))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching commodity master details"));
	}

	/**
	 * @param lmId
	 * @return Location Name
	 */
	@ApiOperation(value = "Get Location name based on ID")
	@GetMapping("/get-location-name/{lmId}")
	public ResponseEntity<String> getLocationById(@PathVariable("lmId") Integer lmId) {

		return Optional.ofNullable(locationMasterService.getLocationMasterById(lmId))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching location master details"));
	}

	@ApiOperation(value = "Get total Commodity Pricing details")
	@GetMapping("/get-all-commodity-pricing-details")
	public ResponseEntity<List<CommodityMasterDTO2>> getCommodityPricingDetails()
			throws RuntimeException, CommodityPricingNotFound {

		List<CommodityMasterDTO> commodityMasterDTOs = commodityService.getCommodityDetails();

		List<CommodityMasterDTO2> commodityMasterDTO2s = new ArrayList<>();

		for (CommodityMasterDTO dto : commodityMasterDTOs) {

			CommodityMasterDTO2 dto2 = new CommodityMasterDTO2();
			dto2.setCommodityId(dto.getCommodityId());
			dto2.setCommodityName(dto.getCommodityName());
			dto2.setLocationName(dto.getLocationName());
			dto2.setPrice(dto.getPrice());
			dto2.setUnits(dto.getUnits());
			dto2.setUserName(dto.getUserName());
			commodityMasterDTO2s.add(dto2);
		}

		return Optional.ofNullable(commodityMasterDTO2s).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching location master details"));
	}

	@ApiOperation(value = "Get last 5 Commodity Pricing details")
	@GetMapping("/get-last-5-commodity-pricing-details")
	public ResponseEntity<List<CommodityMasterDTO>> getLast5CommodityPricingDetails()
			throws RuntimeException, CommodityPricingNotFound {

		List<CommodityMasterDTO> commodityMasterDTOs = commodityService.getCommodityDetails().stream()
				.sorted(Comparator.comparingInt(CommodityMasterDTO::getCommodityId).reversed()).limit(5)
				.collect(Collectors.toList());

		return Optional.ofNullable(commodityMasterDTOs).map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in fetching location master details"));
	}
}
