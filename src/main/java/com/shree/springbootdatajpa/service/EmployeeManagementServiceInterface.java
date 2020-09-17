package com.shree.springbootdatajpa.service;

import java.util.List;

import com.shree.springbootdatajpa.entities.Employee;
import com.shree.springbootdatajpa.exception.EmployeeNotFound;
import com.shree.springbootdatajpa.vo.EmployeeVo;

public interface EmployeeManagementServiceInterface {

	public Employee createEmployee(EmployeeVo employeeVo);
	public EmployeeVo getEmployeeById(int id) throws EmployeeNotFound;
	public List<EmployeeVo> findAllEmp();
}
