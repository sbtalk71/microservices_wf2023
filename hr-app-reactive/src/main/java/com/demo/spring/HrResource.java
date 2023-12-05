package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@RestController

public class HrResource {

	@Autowired
	WebClient.Builder builder;
	
	@GetMapping(path="/hr/emp/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<String> getEmpInfo(@PathVariable("id") int id) {
		
		Mono<String> response=builder.baseUrl("http://emp-service/emp").build()
								.get()
								.uri("/"+id)
								.retrieve().bodyToMono(String.class);
		
		return response;
		
		
		
	}
}
