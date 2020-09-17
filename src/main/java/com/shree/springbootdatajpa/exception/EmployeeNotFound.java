package com.shree.springbootdatajpa.exception;

public class EmployeeNotFound extends Exception{

	String Message;

	public EmployeeNotFound(int id) {
		super(String.format("Employee with Id "+ id +" not found"));
	}
	
}
