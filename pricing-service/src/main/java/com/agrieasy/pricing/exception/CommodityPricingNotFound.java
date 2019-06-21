package com.agrieasy.pricing.exception;

/**
 * @author sumilon.mondal
 *
 */
public class CommodityPricingNotFound extends Exception {

	private static final long serialVersionUID = -9079454849611061074L;

	public CommodityPricingNotFound() {
		super();
	}

	/**
	 * @param message
	 */
	public CommodityPricingNotFound(final String message) {
		super(message);
	}
}
