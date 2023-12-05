package com.demo.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.Emp;
import com.demo.spring.repo.EmpRepository;

@RestController
public class EmpResource {

	@Autowired
	private EmpRepository empRepository;

	@RequestMapping(path="/emp/greet",method = RequestMethod.GET,produces = MediaType.TEXT_PLAIN_VALUE)
	public String greet() {
		return "Hello from Spring REST";
	}
	
	@RequestMapping(path="/emp/",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Emp> getAllEmps(){
		return empRepository.findAll();
	}
	
}
