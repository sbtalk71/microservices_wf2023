package com.demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.Emp;

@RestController
public class KafkaProducerResource {

	@Autowired
	private KafkaTemplate<String, String> kt;
	
	@GetMapping(path="/send", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> sendMessage(@RequestParam("msg") String message){
		kt.send("demo-topic", message);
		return ResponseEntity.ok("Message Sent");
	}
	
	//JSON Handler
	@Autowired
	@Qualifier("jsonKafkaTemplate")
	private KafkaTemplate<String, Emp> jsonKafkaTemplate;
	
	@PostMapping(path="/sendJson", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity sendEmp( @RequestBody Emp e) {
		jsonKafkaTemplate.send("demo-topic",e);
		return ResponseEntity.ok("Emp Data Sent");
	}
	
	
}
