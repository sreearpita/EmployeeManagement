package com.shree.springbootdatajpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.hibernate.boot.model.source.internal.hbm.Helper;
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
public class EmployeeManagementService implements EmployeeManagementServiceInterface {

	@Autowired
	private EmployeeManagementDao employeeManagementDao;
	@Autowired
	private EmployeeManagementAddressDao employeeManagementAddressDao;
	@Autowired
	private EmployeeManagementIdentityDao employeeManagementIdentityDao;
	@Autowired
	private DozerBeanMapper mapper;

	// DozerBeanMapper mapper = new DozerBeanMapper();

	/**
	 * This method is used to create a employee
	 *
	 * @param employeeVo
	 * 
	 */
	public Employee createEmployee(EmployeeVo employeeVo) {

		log.debug("emp before saving" + employeeVo + " address list: " + employeeVo.getAddressDetail());

		List<EmployeeAddress> employeeAddressList = new ArrayList<>();
		employeeVo.getAddressDetail().stream().forEach(employeeAddressVo -> {
			EmployeeAddress employeeAddress = mapper.map(employeeAddressVo, EmployeeAddress.class);
			employeeAddressList.add(employeeAddress);
		});
		employeeManagementAddressDao.saveAll(employeeAddressList);
		log.debug("received address dao obj after save in database" + employeeAddressList);
		EmployeeIdentity empIdentity = mapper.map(employeeVo.getIdentity(), EmployeeIdentity.class);
		employeeManagementIdentityDao.save(empIdentity);

		Employee emp = mapper.map(employeeVo, Employee.class);
		emp.setAddress(employeeAddressList);
		emp.setIdentity(empIdentity);
		Employee result = employeeManagementDao.save(emp);
		log.debug("emp value" + emp);
		return result;
		/*
		 * if (result !=null) { return 1;} else { return 0; }
		 */

	}

	/**
	 * this method gets all employees by a given id
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public EmployeeVo getEmployeeById(int id) throws EmployeeNotFound {
		Employee employee = employeeManagementDao.findById(id);
		if (employee == null) {
			throw new EmployeeNotFound(id);
		} else {
			EmployeeVo employeeVo = mapper.map(employee, EmployeeVo.class);
			return employeeVo;
		}
	}

	/**
	 * this method gets details of all employees
	 * 
	 *
	 * @return
	 * 
	 */
	public List<EmployeeVo> findAllEmp() {
		List<Employee> employees = employeeManagementDao.findAll();
		List<EmployeeVo> employeeVos = mapper.map(employees, List.class);
		return employeeVos;
	}

	/**
	 * this method gets all employees by a given name
	 * 
	 * @param name
	 * @return
	 * 
	 */
	public List<EmployeeVo> getEmployeeByName(String name) {
		List<Employee> emps = employeeManagementDao.findByName(name);
		List<EmployeeVo> empVos = mapper.map(emps, List.class);
//		if (emps == null) {
//			throw new NoDataFound(name);
//		}
		return empVos;
	}

}
