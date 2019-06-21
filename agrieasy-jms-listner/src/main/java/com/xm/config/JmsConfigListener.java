package com.xm.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class JmsConfigListener {

	@JmsListener(destination = "${unimoni.activemq.broker.queue}")
	public void receive(String message) throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("Recieved Message: " + message);
		
		SimpleClientHttpRequestFactory clientHttpReq = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("webproxy.uaeexchange.com", 8080));
		clientHttpReq.setProxy(proxy);
		RestTemplate restTemplate = new RestTemplate(clientHttpReq);
		
		MultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        String requestBody = "{\"email\": \"sumilon.mondal@unimoni.com\",\"message\": \"Hi,\\nStatus of your order : "+message+"\",\"subject\": \"Agrieasy\"}";
        HttpEntity request = new HttpEntity(requestBody, headers);
		
		restTemplate.postForEntity("http://gps-trackapp.1d35.starter-us-east-1.openshiftapps.com:80/track/sendMail",
				request, String.class);
	}
}
