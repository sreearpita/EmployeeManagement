package com.shree.springbootdatajpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
this class creates a entity for Employee Address
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EmployeeAddress")
@Table(name = "employee_address")
public class EmployeeAddress {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "address_id")
	private int id;

	@Column(name = "appartment_number")
	private int apartmentNumber;

//	 @Column(name = "city") private String city;	  
//	 @Column(name = "pincode") private int pincode;
	 
}
