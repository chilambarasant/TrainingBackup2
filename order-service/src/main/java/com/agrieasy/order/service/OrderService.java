package com.agrieasy.order.service;

import java.util.List;

import com.agrieasy.order.DTO.OrderDTO;
import com.agrieasy.order.exception.PricingNotFound;
import com.agrieasy.order.model.OrderDetails;

/**
 * @author sumilon.mondal
 *
 */
public interface OrderService {

	/**
	 * @param order
	 * @return Success / Failure
	 * @throws PricingNotFound
	 */
	public String saveOrderDetails(OrderDetails order) throws PricingNotFound;

	/**
	 * @param orderId
	 * @return OrderDTO Object
	 */
	//public OrderDTO getOrderById(Integer orderId);
	
	public List<OrderDTO> getOrderByUserId(Integer userId);
}
