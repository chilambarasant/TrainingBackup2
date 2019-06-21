package com.agrieasy.uam;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;


@RestController
@RequestMapping("/person")
public class AddMetrics {

	
	private Counter registrations,age20_30,age30plus;
	private Random random = new Random();
	private AtomicInteger recentAge = new AtomicInteger(0);
	
	//private Gauge evenNumber;
	
	@Autowired
	MeterRegistry registry;
	
	@PostConstruct
	public void setupCounter() {
		this.registrations = registry.counter("registrations");
		this.age20_30 = registry.counter("ageGroup","age","age20_30");
		this.age30plus = registry.counter("ageGroup2", "age","age30plus");
		//evenNumber = Gauge.builder("age30PlusCompared", recentAge, AtomicInteger::get).register(registry);
	}
	
	@RequestMapping("/increment/{val}")
	public void setCount(@PathVariable Integer val) {
		if(val==1) {
			 registrations.increment();
		}else {
			 age20_30.increment();
		}
		
	}
	
	
}
