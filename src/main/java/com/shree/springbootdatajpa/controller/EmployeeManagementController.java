package com.shree.springbootdatajpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	public String createEmployee(@RequestBody EmployeeVo employeeVo) {
		log.debug("controller address obj ", employeeVo.getAddress());
		log.debug("controller full emp obj ", employeeVo);
		empService.createEmployee(employeeVo);
		return employeeVo.getName();
	}

	/**
	 * This method is used to fetch employee details by their id
	 * 
	 * @param id
	 * @return
	 * @throws EmployeeNotFoundException
	 */
	@GetMapping("/{id}")
	public EmployeeVo getEmployeeById(@PathVariable("id") int id) throws Exception{
		return empService.getEmployeeById(id);
	}

	/**
	 * This method will return the list of all the employee
	 *
	 * @return
	 */
	@GetMapping("/vo")
	public List<EmployeeVo> getAllEmp() {
		return empService.findAllEmp();
	}

	/**
	 * 
	 * @param empName
	 * @return
	 * @throws NoDataFound
	 */
	@GetMapping("employee/{name}")
	public List<EmployeeVo> getEmpbyName(@PathVariable("name") String name) {
		return empService.getEmployeeByName(name);
	}

}
