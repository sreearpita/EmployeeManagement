package com.shree.springbootdatajpa.entities;

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
this class creates a entity for Employee Identity
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="EmployeeIdentity")
@Table(name="employee_identity")
public class EmployeeIdentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "identity_sequence", allocationSize = 1, initialValue = 1)
	private int id;
	private String IdType;

}
