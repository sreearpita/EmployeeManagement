package com.shree.springbootdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.shree.springbootdatajpa.dao.EmployeeManagementDao;
import com.shree.springbootdatajpa.entities.Employee;
import com.shree.springbootdatajpa.exception.EmployeeNotFound;
import com.shree.springbootdatajpa.service.EmployeeManagementService;
import com.shree.springbootdatajpa.vo.EmployeeAddressVo;
import com.shree.springbootdatajpa.vo.EmployeeIdentityVo;
import com.shree.springbootdatajpa.vo.EmployeeVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeManagementTest {

	@Autowired
	private EmployeeManagementService employeeManagementService;
	@MockBean
	private EmployeeManagementDao employeeManagementDao;

	@Test
	public void findAllEmpTest() {
		when(employeeManagementDao.findAll())
				.thenReturn(Stream.of(new Employee(1, "abc", 22, null, null), new Employee(2, "xyz", 23, null, null))
						.collect(Collectors.toList()));
		assertEquals(2, employeeManagementService.findAllEmp().size());
	}

	@Test
	public void createEmployeeTest() {
		EmployeeIdentityVo identityVo = new EmployeeIdentityVo(1, "voter-id card");
		EmployeeAddressVo addressVo = new EmployeeAddressVo(1, 999);
		List<EmployeeAddressVo> addressVoList = new ArrayList<>();
		addressVoList.add(addressVo);
		EmployeeVo employeeVo = new EmployeeVo(1, "arpss", 12, addressVoList, identityVo);
		Employee employee = new DozerBeanMapper().map(employeeVo, Employee.class);
		log.debug("vo{}", employeeVo);
		System.out.println(employeeVo + "employee vo");
		when(employeeManagementDao.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeManagementService.createEmployee(employeeVo));
		log.debug("employee{}", employee);

	}

	@Test
	public void getEmployeeByIdTest() throws EmployeeNotFound {
		int id = 1;
		Employee employee = new Employee(1, "shrr", 10, null, null);
		EmployeeVo expected = new DozerBeanMapper().map(employee, EmployeeVo.class);
		when(employeeManagementDao.findById(id)).thenReturn(employee);
		assertEquals(expected, employeeManagementService.getEmployeeById(id));
	}
	
	@Test
    public void getEmpByIdTestException() throws Exception {
        int id = 2;
        Employee employee = new Employee(1, "shrr", 10, null, null);
        when(employeeManagementDao.findById(id)).thenReturn(null);
        assertThrows(EmployeeNotFound.class, () -> {
            employeeManagementService.getEmployeeById(id);
        });
	}
}
