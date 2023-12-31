package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.Emp;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/hr")
public class HrResource {

	@Autowired
	RestTemplate rt;
	
	@GetMapping(path="/emp/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@CircuitBreaker(name="hr-app-finder",fallbackMethod = "getEmpInfoFallback")
	@Timed("hr.empinfo")
	public ResponseEntity getEmpInfo(@PathVariable("id") int id) {
		System.out.println("inside hr : getEmpInfo..");
		return rt.getForEntity("http://emp-service/emp/"+id, Emp.class);
		
	}
	
	@GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed("hr.allemps")
	public ResponseEntity getAllData() {
		System.out.println("inside hr : getEmpInfo..");
		return rt.getForEntity("http://emp-service/emp/", String.class);
		
	}
	
	public ResponseEntity getEmpInfoFallback(int id,Exception ex) {
		System.out.println("inside hr : getEmpInfo fallback..");
		return ResponseEntity.ok("Service is down, please try later ");
	}
}
