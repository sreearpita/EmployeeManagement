package com.shree.springbootdatajpa.vo;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.dozer.Mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
this class creates a VO object for Employee Address
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAddressVo {
	
	private int id;
	@Mapping("apartmentNumber")
    private int flatNumber;
//    private String city;
//    private int pincode;

}
