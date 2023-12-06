package com.demo.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Emp;
import com.demo.spring.repo.EmpReactiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/emp")
public class EmpReactiveResource {
	
	@Autowired
	private EmpReactiveRepository repository;

	@GetMapping(path="/{empid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Emp> findEmpById(@PathVariable("empid") int id){
		
		return repository.findById(id);
	}
	
	@GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<Emp> getAll(){
		
		return repository.findAll();
	}
	
	
}
