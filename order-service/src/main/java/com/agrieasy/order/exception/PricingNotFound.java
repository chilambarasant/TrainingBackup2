package com.agrieasy.order.exception;

/**
 * @author sumilon.mondal
 *
 */
public class PricingNotFound extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public PricingNotFound() {
		super();
	}

	/**
	 * @param message
	 */
	public PricingNotFound(final String message) {
		super(message);
	}
}
