package com.demo.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.Emp;
import com.demo.spring.repo.EmpRepository;
import com.demo.spring.util.ResponseData;

@RestController
@RequestMapping("/emp")
public class EmpResource {

	@Autowired
	private EmpRepository empRepository;

	@RequestMapping(path = "/greet", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String greet() {
		return "Hello from Spring REST";
	}

	@RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Emp>> getAllEmps() {
		System.out.println(this.empRepository.getClass().getName());
		return ResponseEntity.ok(empRepository.findAll());
	}

	@GetMapping(path = "/{empid}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity findById(@PathVariable("empid") int id, ServletRequest req) {

		System.out.println("Request Processessed By : " + req.getLocalAddr() + ":" + req.getLocalPort());

		Optional<Emp> empOp = empRepository.findById(id);
		if (empOp.isPresent()) {
			return ResponseEntity.ok(empOp.get());
		} else {
			return ResponseEntity.status(404).body(new ResponseData("Employee Not Found"));
		}
	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> insert(@RequestBody Emp emp) {

		if (empRepository.existsById(emp.getEmpId())) {
			return ResponseEntity.ok(new ResponseData("Employee Exists"));
		} else {
			empRepository.save(emp);
			return ResponseEntity.ok(new ResponseData("Employee Saved"));
		}
	}

	@PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> update(@RequestBody Emp emp) {

		if (empRepository.existsById(emp.getEmpId())) {
			empRepository.save(emp);
			return ResponseEntity.ok(new ResponseData("Employee Updated"));
		} else {

			return ResponseEntity.ok(new ResponseData("Employee Not Found"));
		}
	}

	@DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseData> remove(@RequestParam(name = "empid", required = true) int id) {

		if (empRepository.existsById(id)) {
			empRepository.deleteById(id);
			return ResponseEntity.ok(new ResponseData("Employee Deleted"));
		} else {

			return ResponseEntity.ok(new ResponseData("Employee Not Found"));
		}
	}

	@GetMapping(path = "/ex/{empid1}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Emp> findById2(@PathVariable("empid1") int id, ServletRequest req)
			throws EmpNotFoundException {

		System.out.println("Request Processessed By : " + req.getLocalAddr() + ":" + req.getLocalPort());

		Optional<Emp> empOp = empRepository.findById(id);
		if (empOp.isPresent()) {
			return ResponseEntity.ok(empOp.get());
		} else {
			throw new EmpNotFoundException();
		}
	}

	/*
	@ExceptionHandler(EmpNotFoundException.class)
	public ResponseEntity<ResponseData> handleMyException(EmpNotFoundException ex) {
		return ResponseEntity.ok(new ResponseData("Employee Not Found in DB"));
	}
	*/

}
