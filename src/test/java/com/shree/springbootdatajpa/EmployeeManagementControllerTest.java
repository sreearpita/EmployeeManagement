package com.shree.springbootdatajpa;

import com.shree.springbootdatajpa.controller.EmployeeManagementController;
import com.shree.springbootdatajpa.dao.EmployeeManagementDao;
import com.shree.springbootdatajpa.entities.Employee;
import com.shree.springbootdatajpa.exception.EmployeeNotFound;
import com.shree.springbootdatajpa.vo.EmployeeAddressVo;
import com.shree.springbootdatajpa.vo.EmployeeIdentityVo;
import com.shree.springbootdatajpa.vo.EmployeeVo;

import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class EmployeeManagementControllerTest {

	@Autowired
	EmployeeManagementController employeeController;
	@MockBean
	EmployeeManagementDao employeeRepository;

	/**
	 * method for testing GetAllEmp() controller
	 * 
	 * @throws EmployeeNotFound
	 */
	@Test
	public void testGetAllEmp() throws EmployeeNotFound {
		when(employeeRepository.findAll())
				.thenReturn(Stream.of(new Employee(1, "xyz", 20, null, null), new Employee(1, "xyz", 20, null, null))
						.collect(Collectors.toList()));
		assertEquals(HttpStatus.OK, employeeController.getAllEmp().getStatusCode());
	}

	/**
	 * method for testing GetEmployeeById() controller
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetEmployeeById() throws Exception {
		int id = 1;
		Employee employee = new Employee(1, "xyz", 20, null, null);
		EmployeeVo expected = new DozerBeanMapper().map(employee, EmployeeVo.class);
		when(employeeRepository.findById(id)).thenReturn(employee);
		assertEquals(HttpStatus.OK, employeeController.getEmployeeById(id).getStatusCode());
	}

	/**
	 * method for testing CreateEmployee() controller
	 */
	@Test
	public void testCreateEmployee() {
		EmployeeIdentityVo identityVo = new EmployeeIdentityVo(1, "abc");
		EmployeeAddressVo addressVo = new EmployeeAddressVo(1, 403);
		List<EmployeeAddressVo> addressVOList = new ArrayList<>();
		addressVOList.add(addressVo);
		EmployeeVo employeeVO = new EmployeeVo(1, "pqrs", 66, addressVOList, identityVo);
		Employee employee = new DozerBeanMapper().map(employeeVO, Employee.class);
		log.debug("vo{}", employeeVO);
		when(employeeRepository.save(employee)).thenReturn(employee);
		assertEquals(HttpStatus.CREATED, employeeController.createEmployee(employeeVO).getStatusCode());
	}
}