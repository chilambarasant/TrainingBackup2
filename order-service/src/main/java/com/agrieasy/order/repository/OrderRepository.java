package com.agrieasy.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agrieasy.order.model.OrderDetails;

/**
 * @author sumilon.mondal
 *
 */
public interface OrderRepository extends JpaRepository<OrderDetails, Integer> {

	/**
	 * @param ordId - orderId
	 * @return OrderDetails Object
	 */
	public OrderDetails findByOrdId(Integer ordId);
	
	public List<OrderDetails> findByCreatedBy(Integer createdBy);
}
