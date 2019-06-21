package com.agrieasy.pricing.utility;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author sumilon.mondal
 *
 */
@Configuration
public class Utility {

	@Autowired
	ReloadableResourceBundleMessageSource messageSource;

	/**
	 * @param messageCode
	 * @return messageDescription in String
	 */
	public String getMessageDescription(String messageCode) {

		return messageSource.getMessage(messageCode, new Object[0], Locale.ENGLISH);
	}
}
