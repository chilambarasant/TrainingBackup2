package com.agrieasy.order.utility;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * @author sumilon.mondal
 *
 */
@Configuration
public class Utility {

	@Autowired
	ReloadableResourceBundleMessageSource messageSource;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@Value("${PRICING-SERVICE-URL}")
	private String pricingServiceURL;

	/**
	 * @param messageCode
	 * @return messageDescription in String
	 */
	public String getMessageDescription(String messageCode) {

		return messageSource.getMessage(messageCode, new Object[0], Locale.ENGLISH);
	}

	/**
	 * @return baseurl for pricing service (name registered in Eureka)
	 */
	public String getBaseUrl() {

		ServiceInstance serviceInstance = loadBalancer.choose(pricingServiceURL);
		System.out.println(serviceInstance.getUri());
		String baseUrl = serviceInstance.getUri().toString();
		return baseUrl;
	}

	/**
	 * @return Header details for restful service
	 * @throws IOException
	 */
	public HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}
