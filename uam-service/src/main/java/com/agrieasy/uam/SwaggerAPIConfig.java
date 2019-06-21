package com.agrieasy.uam;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerAPIConfig {
	/**
	 * @return Swagger API Informations .
	 */

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.agrieasy.uam.api"))
				.paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Agri-Easy-Uam",
				"UAM Service API\", \"UAM Service - User can signup and login,change passwords)",
				"SNAPSHOT-0.0.1", "GET,POST,PUT,DELETE", new Contact("Manivel P", "/test-api/", "manivel@unimoni.com"), "UNIMONI LLC", "www.unimoni.com",new ArrayList<>());
		return apiInfo;
	}
}