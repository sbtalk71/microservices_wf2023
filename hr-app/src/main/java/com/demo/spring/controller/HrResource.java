package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.Emp;

@RestController
public class HrResource {

	@Autowired
	RestTemplate rt;
	
	@GetMapping(path="/hr/emp/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getEmpInfo(@PathVariable("id") int id) {
		
		return rt.getForEntity("http://localhost:8080/emp/"+id, Emp.class);
		
	}
}
