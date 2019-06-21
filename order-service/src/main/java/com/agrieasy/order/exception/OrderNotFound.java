package com.agrieasy.order.exception;

/**
 * @author sumilon.mondal
 *
 */
public class OrderNotFound extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public OrderNotFound() {
		super();
	}

	/**
	 * @param message
	 */
	public OrderNotFound(final String message) {
		super(message);
	}
}
