package com.shree.springbootdatajpa.exception;

public class EmployeeNotFound extends Exception {
    public EmployeeNotFound(int id) {
        super(String.format("Employee Not Found of id %d ", id));
    }
}