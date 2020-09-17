package com.shree.springbootdatajpa.vo;

import javax.persistence.Column;
import javax.persistence.Entity;

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
    private int apartmentNumber;
//    private String city;
//    private int pincode;

}
