package com.agrieasy.order.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrieasy.order.DTO.OrderDTO;
import com.agrieasy.order.exception.PricingNotFound;
import com.agrieasy.order.model.OrderDetails;
import com.agrieasy.order.repository.OrderRepository;
import com.agrieasy.order.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author sumilon.mondal
 *
 */
@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
@Api(value = "pricing-service", description = "All Operations for Ordering Commodity")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${unimoni.activemq.broker.queue}")
	String queue;

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * @param order
	 * @return Success/Failure
	 * @throws RuntimeException
	 * @throws PricingNotFound
	 */
	@PostMapping("/save_order_details")
	public ResponseEntity<String> saveOrderDetails(@RequestBody OrderDetails order)
			throws RuntimeException, PricingNotFound {

		Date currentDate = new Date();
		System.out.println(dateFormat.format(currentDate));

		// convert date to calendar
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);

		c.add(Calendar.DATE, 5);
		Date currentDatePlusFive = c.getTime();

		OrderDetails orderDetails = order;
		orderDetails.setOrdDate(currentDate);
		orderDetails.setDeliveryDate(currentDatePlusFive);
		orderDetails.setOrderStatus("ORDER PLACED");
		orderDetails.setOrdCreatedOn(new Date());

		jmsTemplate.convertAndSend(queue, "ORDER PLACED");

		return Optional.ofNullable(orderService.saveOrderDetails(orderDetails))
				.map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
				.orElseThrow(() -> new RuntimeException("Order creation failed"));
	}

	/*@GetMapping("/checkjms")
	public String checkJMS() {

		jmsTemplate.convertAndSend(queue, "ORDER PLACED");
		return "Success";
	}*/

	/**
	 * @param order
	 * @return Success/Failure
	 * @throws RuntimeException
	 * @throws PricingNotFound
	 */
	@PostMapping("/update_order_details")
	public ResponseEntity<String> updateOrderDetails(@RequestBody OrderDetails order)
			throws RuntimeException, PricingNotFound {

		OrderDetails orderDetails = orderRepository.findByOrdId(order.getOrdId());

		order.setOrdId(orderDetails.getOrdId());
		order.setOrdCpId(orderDetails.getOrdCpId());
		order.setCpAddress(orderDetails.getCpAddress());
		order.setDeliveryDate(orderDetails.getDeliveryDate());
		order.setOrdDate(orderDetails.getOrdDate());
		order.setCreatedBy(orderDetails.getCreatedBy());
		order.setOrdCreatedOn(orderDetails.getOrdCreatedOn());
		order.setOrderStatus("ORDER ACCEPTED");
		
		jmsTemplate.convertAndSend(queue, "ORDER ACCEPTED");

		return Optional.ofNullable(orderService.saveOrderDetails(order))
				.map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
				.orElseThrow(() -> new RuntimeException("Order creation failed"));
	}

	/**
	 * @param ordId - orderId
	 * @return OrderDTO object
	 */
	@ApiOperation(value = "Get Order Details based on ID")
	@GetMapping("/get_order_details/{userId}")
	public ResponseEntity<List<OrderDTO>> getOrderDetailsById(@PathVariable("userId") Integer userId) {

		return Optional.ofNullable(orderService.getOrderByUserId(userId))
				.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
				.orElseThrow(() -> new RuntimeException("Error in order details"));
	}

}
