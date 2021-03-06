package com.shree.springbootdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shree.springbootdatajpa.entities.Employee;
import com.shree.springbootdatajpa.service.EmployeeManagementService;
import com.shree.springbootdatajpa.vo.EmployeeVo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

/*the controller EmployeeManagementController class exposes the APIs */
@Slf4j
@RestController("/emp")
public class EmployeeManagementController {

	@Autowired
	private EmployeeManagementService empService;

	/**
	 * This method will create and save the details of an employee
	 *
	 * @param employeeVo
	 */
	@PostMapping("/")
	public ResponseEntity<Integer> createEmployee(@RequestBody EmployeeVo employeeVo) {
		log.debug("controller address obj ", employeeVo.getAddressDetail());
		log.debug("controller full emp obj ", employeeVo);
		int emp=empService.createEmployee(employeeVo);
		//return employeeVo.getFullName();
		return new ResponseEntity<>(emp,HttpStatus.CREATED);
	}

	/**
	 * This method is used to fetch employee details by their id
	 * 
	 * @param id
	 * @return
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeVo> getEmployeeById(@PathVariable("id") int id) throws Exception{
		//return empService.getEmployeeById(id);
		return new ResponseEntity<>(empService.getEmployeeById(id), HttpStatus.OK);
	}

	/**
	 * This method will return the list of all the employee
	 *
	 * @return
	 */
	@GetMapping("/vo")
	public ResponseEntity<List<EmployeeVo>> getAllEmp() {
		//return empService.findAllEmp();
		return new ResponseEntity<>(empService.findAllEmp(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param empName
	 * @return
	 * @throws NoDataFound
	 */
	@GetMapping("employee/{name}")
	public EmployeeVo getEmpbyName(@PathVariable("name") String name) {
		return empService.getEmployeeByName(name);
	}

}
