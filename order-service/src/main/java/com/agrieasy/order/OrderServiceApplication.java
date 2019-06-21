package com.agrieasy.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;

import brave.Tracing;
import brave.opentracing.BraveTracer;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

/**
 * @author sumilon.mondal
 *
 */
@SpringBootApplication
public class OrderServiceApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	// @Bean
	public io.opentracing.Tracer jaegerTracer() {
		return new Configuration("order-service", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
				new Configuration.ReporterConfiguration()).getTracer();
	}

	@Bean
	public io.opentracing.Tracer zipkinTracer() {
		OkHttpSender okHttpSender = OkHttpSender.create("http://localhost:9411/api/v1/spans");
		AsyncReporter<zipkin.Span> reporter = AsyncReporter.builder(okHttpSender).build();
		Tracing braveTracer = Tracing.newBuilder().localServiceName("order-service").reporter(reporter).build();
		return BraveTracer.create(braveTracer);
	}

	public static void main(String[] args) {

		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
