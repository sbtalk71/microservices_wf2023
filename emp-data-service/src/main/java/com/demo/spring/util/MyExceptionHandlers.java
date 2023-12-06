package com.demo.spring.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.spring.controllers.EmpNotFoundException;


@RestControllerAdvice
public class MyExceptionHandlers {

	@ExceptionHandler(EmpNotFoundException.class)
	public ResponseEntity<ResponseData> handleMyException(EmpNotFoundException ex) {
		return ResponseEntity.ok(new ResponseData("Employee Not Found in DB"));
	}
}
