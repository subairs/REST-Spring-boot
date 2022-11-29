package com.subair.Payroll.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subair.Payroll.entity.Employee;
import com.subair.Payroll.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {
	
	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		//super();
		this.employeeRepository = employeeRepository;
	}
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("employee")
	Employee newEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
		
	}
	
	

}
