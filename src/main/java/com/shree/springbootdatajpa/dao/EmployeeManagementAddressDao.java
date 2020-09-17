package com.shree.springbootdatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.shree.springbootdatajpa.entities.EmployeeAddress;

public interface EmployeeManagementAddressDao extends JpaRepository<EmployeeAddress, Integer> {

}
