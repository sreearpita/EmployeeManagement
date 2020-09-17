package com.shree.springbootdatajpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shree.springbootdatajpa.dao.EmployeeManagementAddressDao;
import com.shree.springbootdatajpa.dao.EmployeeManagementDao;
import com.shree.springbootdatajpa.dao.EmployeeManagementIdentityDao;
import com.shree.springbootdatajpa.entities.Employee;
import com.shree.springbootdatajpa.entities.EmployeeAddress;
import com.shree.springbootdatajpa.entities.EmployeeIdentity;
import com.shree.springbootdatajpa.vo.EmployeeIdentityVo;
import com.shree.springbootdatajpa.exception.EmployeeNotFound;
import com.shree.springbootdatajpa.vo.EmployeeAddressVo;
import com.shree.springbootdatajpa.vo.EmployeeVo;

import lombok.extern.slf4j.Slf4j;

import com.shree.springbootdatajpa.vo.EmployeeVo;

@Slf4j
@Service
public class EmployeeManagementService implements EmployeeManagementServiceInterface{

	@Autowired
	private EmployeeManagementDao employeeManagementDao;
	@Autowired
	private EmployeeManagementAddressDao employeeManagementAddressDao;
	@Autowired
	private EmployeeManagementIdentityDao employeeManagementIdentityDao;

	 /**
     * This method is used to create a employee
     *
     * @param employeeManagementVO
     */
	public Employee createEmployee(EmployeeVo employeeVo) {

		log.debug("emp before saving" + employeeVo + " address list: " + employeeVo.getAddress());
		
		List<EmployeeAddress> employeeAddressList = new ArrayList<>();
		employeeVo.getAddress().stream().forEach(employeeAddressVo -> {
			EmployeeAddress employeeAddress = callMapper().map(employeeAddressVo, EmployeeAddress.class);
			employeeAddressList.add(employeeAddress);
		});
		employeeManagementAddressDao.saveAll(employeeAddressList);
		log.debug("received address dao obj after save in database" + employeeAddressList);
		EmployeeIdentity empIdentity = callMapper().map(employeeVo.getIdentity(), EmployeeIdentity.class);
		employeeManagementIdentityDao.save(empIdentity);

		Employee emp = callMapper().map(employeeVo, Employee.class);
		emp.setAddress(employeeAddressList);
		emp.setIdentity(empIdentity);
		employeeManagementDao.save(emp);
		log.debug("emp value" + emp);
		return emp;

	}

	/**
     * This method is used to get the employee detail by their id
     *
     * @param id
     * @return EmployeeVo
	 * @throws EmployeeNotFound 
     */
	public EmployeeVo getEmployeeById(int id) throws EmployeeNotFound {

		Employee empDao = employeeManagementDao.findById(id);
		if(empDao==null) {
			throw new EmployeeNotFound(id);
		}
		EmployeeVo empVo = callMapper().map(empDao, EmployeeVo.class);
		return empVo;			
	}

	/**
     * This method is used to get the employee details of all the employees
     *
     * @return
     */
	public List<EmployeeVo> findAllEmp() {
		List<Employee> employees = employeeManagementDao.findAll();
		List<EmployeeVo> employeeVos = callMapper().map(employees, List.class);
		return employeeVos;
	}

//	public List<EmployeeVo> getEmployeeByName(String name) throws EmployeeNotFound {
//
//		List<Employee> empDao = employeeManagementDao.findByName(name);
//		DozerBeanMapper mapper = new DozerBeanMapper();
//		EmployeeVo empVo = mapper.map(empDao, EmployeeVo.class);
//		return empVo;
//	}
	
	public  DozerBeanMapper callMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper;
	}

}
