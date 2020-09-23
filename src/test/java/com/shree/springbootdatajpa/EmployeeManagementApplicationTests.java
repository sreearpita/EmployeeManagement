package com.shree.springbootdatajpa;

import com.shree.springbootdatajpa.dao.EmployeeManagementDao;
import com.shree.springbootdatajpa.entities.Employee;
import com.shree.springbootdatajpa.entities.EmployeeAddress;
import com.shree.springbootdatajpa.entities.EmployeeIdentity;
import com.shree.springbootdatajpa.exception.EmployeeNotFound;
import com.shree.springbootdatajpa.service.EmployeeManagementService;
import com.shree.springbootdatajpa.vo.EmployeeAddressVo;
import com.shree.springbootdatajpa.vo.EmployeeIdentityVo;
import com.shree.springbootdatajpa.vo.EmployeeVo;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeManagementApplicationTests {

    @Autowired
    private EmployeeManagementService employeeManagementService;
    @MockBean
    private EmployeeManagementDao employeeManagementRepository;
    
    @Test
    public void getAllEmpTest() {
        List<Employee> employeeManagementModels = new ArrayList<>();
        EmployeeIdentity passportM1 = new EmployeeIdentity(1, "pan card");
        EmployeeAddress addressM1 = new EmployeeAddress(1, 999);
        List<EmployeeAddress> addressModelList1 = new ArrayList<>();
        addressModelList1.add(addressM1);
        EmployeeIdentity passportM2 = new EmployeeIdentity(2, "xy");
        EmployeeAddress addressM2 = new EmployeeAddress(2, 409);
        List<EmployeeAddress> addressModelList2 = new ArrayList<>();
        addressModelList2.add(addressM2);
        employeeManagementModels.add(new Employee(1, "xyz", 20, addressModelList1, passportM1));
        employeeManagementModels.add(new Employee(2, "abc", 30, addressModelList2, passportM2));
     
        when(employeeManagementRepository.findAll()).thenReturn(employeeManagementModels);
        assertEquals(2, employeeManagementService.findAllEmp().size());
        List<EmployeeVo> employeeManagementVOS = employeeManagementService.findAllEmp();
        log.debug("model{}", employeeManagementModels);
        log.debug("vo{}", employeeManagementVOS);
        System.out.println("model" + employeeManagementModels);
        System.out.println("vo" + employeeManagementVOS);
        
        assertNotNull(employeeManagementVOS);
       
        EmployeeIdentityVo passportVO = new EmployeeIdentityVo(1, "ab");
        EmployeeIdentityVo passportVO1 = new EmployeeIdentityVo(2, "xy");
        EmployeeAddressVo addressVO = new EmployeeAddressVo(1, 999);
        List<EmployeeAddressVo> addressVOList = new ArrayList<>();
        addressVOList.add(addressVO);
        EmployeeAddressVo addressVO1 = new EmployeeAddressVo(2, 809);
        List<EmployeeAddressVo> addressVOList1 = new ArrayList<>();
        addressVOList1.add(addressVO1);
        employeeManagementVOS.stream().forEach(emp -> {
            if(emp.getId()==1)
            {
                assertEquals(emp.getAge(), 20);
                assertEquals(emp.getFullName(), "xyz");
                assertEquals(emp.getIdentity(), passportVO);
                assertEquals(emp.getAddressDetail(), addressVOList);
            } else {
                assertEquals(emp.getAge(), 30);
                assertEquals(emp.getFullName(), "abc");
                assertEquals(emp.getIdentity(), passportVO1);
                assertEquals(emp.getAddressDetail(), addressVOList1);
            }
        });
    }
//    @Test
//    public void getEmpByIdTest() throws Exception {
//        int id = 1;
//        EmployeeIdentity passportM1 = new EmployeeIdentity(1, "ab");
//        EmployeeAddress addressM1 = new EmployeeAddress(1, 999);
//        EmployeeAddress addressM2=new EmployeeAddress(2,409);
//        List<EmployeeAddress> addressModelList1 = new ArrayList<>();
//        addressModelList1.add(addressM1);
//        addressModelList1.add(addressM2);
//        Employee employeeManagementModel = new Employee(1, "xyz", 20, addressModelList1, passportM1);
//        EmployeeVo expected = new DozerBeanMapper().map(employeeManagementModel, EmployeeVo.class);
//        when(employeeManagementRepository.findById(id)).thenReturn(employeeManagementModel);
//        assertEquals(expected, employeeManagementService.getEmployeeById(id));
//        List<EmployeeVo> employeeManagementVOS = employeeManagementService.findAllEmp();
//        assertNotNull(employeeManagementVOS);
//        EmployeeIdentityVo passportVo = new EmployeeIdentityVo(1, "ab");
//        EmployeeAddressVo addressVo1 = new EmployeeAddressVo(1, 999);
//        EmployeeAddressVo addressVo2 = new EmployeeAddressVo(2, 409);
//        List<EmployeeAddressVo> addressVoList1 = new ArrayList<>();
//        addressVoList1.add(addressVo1);
//        addressVoList1.add(addressVo2);
//        for (EmployeeVo emp : employeeManagementVOS) {
//            assertEquals(emp.getAge(), 20);
//            assertEquals(emp.getFullName(), "xyz");
//            assertEquals(emp.getAddressDetail(), addressVoList1);
//            assertEquals(emp.getIdentity(), passportVo);
//        }
//    }
//    @Test()
//    public void getEmpByIdTestException() throws Exception {
//        int id = 1;
//        when(employeeManagementRepository.findById(id)).thenReturn(null);
//        assertThrows(EmployeeNotFound.class, () -> {
//            employeeManagementService.getEmployeeById(id);
//        });
//    }
//    @Test
//    public void saveEmployeeTest() {
//        EmployeeIdentityVo passportVO = new EmployeeIdentityVo(1, "ab");
//        EmployeeAddressVo addressVO = new EmployeeAddressVo(1, 999);
//        List<EmployeeAddressVo> addressVOList = new ArrayList<>();
//        addressVOList.add(addressVO);
//        EmployeeVo employeeManagementVO = new EmployeeVo(1, "xyz", 66, addressVOList, passportVO);
//        Employee employeeManagementModel = new DozerBeanMapper().map(employeeManagementVO, Employee.class);
//        log.debug("vo{}", employeeManagementVO);
//        System.out.println(employeeManagementVO + "syso");
//        when(employeeManagementRepository.save(employeeManagementModel)).thenReturn(employeeManagementModel);
//        assertEquals(employeeManagementVO, employeeManagementService.createEmployee(employeeManagementVO));
//        List<EmployeeVo> employeeManagementVOS = employeeManagementService.findAllEmp();
//        assertNotNull(employeeManagementVOS);
//        for (EmployeeVo emp : employeeManagementVOS) {
//            assertEquals(emp.getAge(), 66);
//            assertEquals(emp.getFullName(), "xyz");
//            assertEquals(emp.getAddressDetail(), addressVO);
//            assertEquals(emp.getIdentity(), passportVO);
//        }
//    }
//
//    @Test
//    public void getEmpByNameTest() throws Exception {
//        String name = "prerna";
//        Employee employee = new Employee(1, "xyz", 20, null, null);
//        EmployeeVo expected = new DozerBeanMapper().map(employee, EmployeeVo.class);
//        when(employeeManagementRepository.findByName(name)).thenReturn(employee);
//        assertEquals(expected, employeeManagementService.getEmployeeByName(name));
//        List<EmployeeVo> employeeManagementVOS = employeeManagementService.findAllEmp();
//        assertNotNull(employeeManagementVOS);
//        for (EmployeeVo emp : employeeManagementVOS) {
//            assertEquals(emp.getAge(), 20);
//            assertEquals(emp.getFullName(), "xyz");
//            assertEquals(emp.getAddressDetail(), null);
//            assertEquals(emp.getIdentity(), null);
//        }
//        System.out.println("model" + employee);
//        System.out.println("vo" + employeeManagementVOS);
//    }
//    @Test()
//    public void getEmpByNameTestException() throws Exception {
//        String name = "shree";
//        when(employeeManagementRepository.findByName(name)).thenReturn(null);
//        assertThrows(EmployeeNotFound.class, () -> {
//            employeeManagementService.getEmployeeByName(name);
//        });
//    }

}