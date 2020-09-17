package com.shree.springbootdatajpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
this class creates a entity for Employee Identity
*/

@Data
@NoArgsConstructor
@Entity(name="EmployeeIdentity")
@Table(name="employee_identity")
public class EmployeeIdentity {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	private String IdType;

}
