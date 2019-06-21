package com.agrieasy.order.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.agrieasy.order.DTO.CommodityPricingDTO;
import com.agrieasy.order.DTO.OrderDTO;
import com.agrieasy.order.exception.PricingNotFound;
import com.agrieasy.order.model.CommodityPricing;
import com.agrieasy.order.model.OrderDetails;
import com.agrieasy.order.repository.CommodityPricingRepository;
import com.agrieasy.order.repository.OrderRepository;
import com.agrieasy.order.service.OrderService;
import com.agrieasy.order.utility.Utility;

/**
 * @author sumilon.mondal
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CommodityPricingRepository pricingRepository;

	@Autowired
	private Utility utility;

	@Override
	public String saveOrderDetails(OrderDetails order) throws PricingNotFound {

		String checkPricing = "";
		String CHECK_COMMODITY_PRICING_URL = String.format(utility.getBaseUrl() + "/v1/check-commodity-pricing/%s",
				order.getOrdCpId());

		System.out.println("CHECK_COMMODITY_PRICING_URL : " + CHECK_COMMODITY_PRICING_URL);

		try {
			checkPricing = new RestTemplate()
					.exchange(CHECK_COMMODITY_PRICING_URL, HttpMethod.GET, utility.getHeaders(), String.class)
					.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}

		CommodityPricing commodityPricing = new CommodityPricing();
		Optional<CommodityPricing> pricing = pricingRepository.findById(order.getOrdCpId());

		commodityPricing = pricing.get();

		commodityPricing.setCpId(order.getOrdCpId());
		commodityPricing.setCpStatus(0);

		if (checkPricing.equalsIgnoreCase("Available")) {
			try {
				orderRepository.save(order);
				pricingRepository.save(commodityPricing);
				return "Success";
			} catch (Exception e) {
				e.printStackTrace();
				return "Failure";
			}
		} else {
			throw new PricingNotFound(utility.getMessageDescription("NOT_FOUND"));
		}
	}

	/*@Override
	public OrderDTO getOrderById(Integer orderId) {

		String commodityName = null;
		// String userName = null;
		String locationName = null;

		OrderDetails orderDetails = orderRepository.findByOrdId(orderId);

		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setOrdId(orderDetails.getOrdId());
		orderDTO.setOrdDate(orderDetails.getOrdDate());
		//orderDTO.setOrdCpId(orderDetails.getOrdCpId());
		orderDTO.setDeliveryDate(orderDetails.getDeliveryDate());
		orderDTO.setOrderStatus(orderDetails.getOrderStatus());

		try {

			String GET_COMMODITY_BY_ID_URL = String.format(utility.getBaseUrl() + "/v1/get-commodity/%s",
					orderDetails.getOrdCpId());

			System.out.println("GET_COMMODITY_BY_ID_URL : " + GET_COMMODITY_BY_ID_URL);

			ResponseEntity<CommodityPricingDTO> response = new RestTemplate().exchange(GET_COMMODITY_BY_ID_URL,
					HttpMethod.GET, utility.getHeaders(), CommodityPricingDTO.class);

			CommodityPricingDTO dto = response.getBody();

			String COMMODITY_MASTER_BY_ID_URL = String.format(utility.getBaseUrl() + "/v1/get-commodity-name/%s",
					dto.getCpCmId());

			String LOCATION_MASTER_BY_ID_URL = String.format(utility.getBaseUrl() + "/v1/get-location-name/%s",
					dto.getCpLmId());

			commodityName = new RestTemplate()
					.exchange(COMMODITY_MASTER_BY_ID_URL, HttpMethod.GET, utility.getHeaders(), String.class).getBody();

			System.out.println("COMMODITY_MASTER_BY_ID_URL : " + COMMODITY_MASTER_BY_ID_URL);

			locationName = new RestTemplate()
					.exchange(LOCATION_MASTER_BY_ID_URL, HttpMethod.GET, utility.getHeaders(), String.class).getBody();

			System.out.println("LOCATION_MASTER_BY_ID_URL : " + LOCATION_MASTER_BY_ID_URL);

		} catch (Exception e) {
			e.printStackTrace();
		}

		orderDTO.setCpCmName(commodityName);
		orderDTO.setCpLmName(locationName);

		return orderDTO;
	}*/

	@Override
	public List<OrderDTO> getOrderByUserId(Integer userId) {

		String commodityName = null;
		// String userName = null;
		String locationName = null;

		List<OrderDetails> orderDetailsList = orderRepository.findByCreatedBy(userId);
		
		DateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");

		List<OrderDTO> orderDTOs = new ArrayList<>();

		for (OrderDetails orderDetails : orderDetailsList) {
			OrderDTO orderDTO = new OrderDTO();

			orderDTO.setOrdId(orderDetails.getOrdId());
			orderDTO.setOrdDate(outputFormat.format(orderDetails.getOrdDate()));
			//orderDTO.setOrdCpId(orderDetails.getOrdCpId());
			orderDTO.setDeliveryDate(outputFormat.format(orderDetails.getDeliveryDate()));
			orderDTO.setOrderStatus(orderDetails.getOrderStatus());
			orderDTO.setCpAddress(orderDetails.getCpAddress());
			
			Optional<CommodityPricing> comPricing = pricingRepository.findById(orderDetails.getOrdCpId());
			
			CommodityPricing commodityPricing = comPricing.get();
			
			orderDTO.setCpUnits(commodityPricing.getCpUnits());

			try {

				String GET_COMMODITY_BY_ID_URL = String.format(utility.getBaseUrl() + "/v1/get-commodity/%s",
						orderDetails.getOrdCpId());

				System.out.println("GET_COMMODITY_BY_ID_URL : " + GET_COMMODITY_BY_ID_URL);

				ResponseEntity<CommodityPricingDTO> response = new RestTemplate().exchange(GET_COMMODITY_BY_ID_URL,
						HttpMethod.GET, utility.getHeaders(), CommodityPricingDTO.class);

				CommodityPricingDTO dto = response.getBody();

				String COMMODITY_MASTER_BY_ID_URL = String.format(utility.getBaseUrl() + "/v1/get-commodity-name/%s",
						dto.getCpCmId());

				String LOCATION_MASTER_BY_ID_URL = String.format(utility.getBaseUrl() + "/v1/get-location-name/%s",
						dto.getCpLmId());

				commodityName = new RestTemplate()
						.exchange(COMMODITY_MASTER_BY_ID_URL, HttpMethod.GET, utility.getHeaders(), String.class)
						.getBody();

				System.out.println("COMMODITY_MASTER_BY_ID_URL : " + COMMODITY_MASTER_BY_ID_URL);

				locationName = new RestTemplate()
						.exchange(LOCATION_MASTER_BY_ID_URL, HttpMethod.GET, utility.getHeaders(), String.class)
						.getBody();

				System.out.println("LOCATION_MASTER_BY_ID_URL : " + LOCATION_MASTER_BY_ID_URL);

			} catch (Exception e) {
				e.printStackTrace();
			}

			orderDTO.setCpCmName(commodityName);
			orderDTO.setCpLmName(locationName);

			orderDTOs.add(orderDTO);
		}

		return orderDTOs;
	}

}
