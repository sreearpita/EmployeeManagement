package com.shree.springbootdatajpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
this class creates a VO object for Employee Identity
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIdentityVo {
	
	private int id;
	private String IdType;

}
