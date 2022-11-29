package com.subair.Payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subair.Payroll.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
