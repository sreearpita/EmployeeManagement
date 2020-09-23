package com.shree.springbootdatajpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shree.springbootdatajpa.entities.Employee;

public interface EmployeeManagementDao extends JpaRepository<Employee, Integer> {

	Employee findById(int id);
	//List<Employee> findByName(String name);
	Employee findByName(String name);
}
