package com.demo.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
	
	

}
