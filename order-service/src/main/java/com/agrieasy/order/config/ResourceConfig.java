package com.agrieasy.order.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author sumilon.mondal
 *
 */
@Configuration
public class ResourceConfig {

	/**
	 * @return MessageSource
	 */
	@Bean
	public MessageSource messageSource() {
		Locale.setDefault(Locale.ENGLISH);
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.addBasenames("classpath:errormessage/messages");
		return messageSource;
	}
}
