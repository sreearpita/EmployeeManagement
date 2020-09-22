package com.shree.springbootdatajpa.service;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeManagementServiceHelper {
	
	@Bean
	public DozerBeanMapper mapper() {
		DozerBeanMapper mapping= new DozerBeanMapper();
		return mapping;
	}

}
