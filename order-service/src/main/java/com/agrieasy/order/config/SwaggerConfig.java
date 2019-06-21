package com.agrieasy.order.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sumilon.mondal
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * @return Docket
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.agrieasy.order.controller")).paths(regex("/v1.*"))
				.build().apiInfo(metaData());
	}

	/**
	 * @return ApiInfo
	 */
	private ApiInfo metaData() {
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo("Order Service API", "Operations with Order Details", "1.0", "Terms of service",
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", "");
		return apiInfo;
	}
}
