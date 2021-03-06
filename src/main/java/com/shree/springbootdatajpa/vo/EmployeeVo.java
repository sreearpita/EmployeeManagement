package com.shree.springbootdatajpa.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.dozer.Mapping;

import com.shree.springbootdatajpa.entities.EmployeeAddress;
//import com.shree.springbootdatajpa.entities.EmployeeIdentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
this class creates a VO object for Employee
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo {
	
	private int id;
	@Mapping("name")
	private String fullName;
	private int age;
	@Mapping("address")
	private List<EmployeeAddressVo> addressDetail=new ArrayList<>();
	private EmployeeIdentityVo identity;
}
