package com.shree.springbootdatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shree.springbootdatajpa.entities.EmployeeIdentity;

public interface EmployeeManagementIdentityDao extends JpaRepository<EmployeeIdentity, Integer> {

}
