package com.shree.springbootdatajpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "address_sequence", allocationSize = 1, initialValue = 1)
	@Column(name = "address_id")
	private int id;

	@Column(name = "appartment_number")
	private int apartmentNumber;

//	 @Column(name = "city") private String city;	  
//	 @Column(name = "pincode") private int pincode;
	 
}
