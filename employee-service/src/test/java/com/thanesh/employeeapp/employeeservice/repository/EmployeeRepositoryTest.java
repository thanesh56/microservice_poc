package com.thanesh.employeeapp.employeeservice.repository;

import com.thanesh.employeeapp.employeeservice.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Test 1:Save Employee Test")
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest() {

        //Action
        Employee employee = new Employee(1, "Thanesh", "thanesh56@gmail.com", "A+");

        employeeRepository.save(employee);

        //Verify
        System.out.println(employee);
        Assertions.assertTrue(employee.getId() > 0, "Employee Id mismatch");
    }

    @Test
    @Order(2)
    public void getEmployeeTest() {

        //Action
        int id = 1;
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        //Verify
        Assertions.assertTrue(employeeOptional.isPresent());
        System.out.println(employeeOptional.get());
        Assertions.assertEquals(id, employeeOptional.get().getId(), "Employee Id mismatch");
    }

    @Test
    @Order(3)
    public void getListOfEmployeeTest() {

        //Action
        List<Employee> employees = employeeRepository.findAll();

        //Verify
        System.out.println(employees);
        Assertions.assertTrue(employees.size() > 0, "Employee count mismatch");

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {

        //Action
        int id = 1;
        Employee employee = employeeRepository.findById(id).get();
        employee.setEmail("thanesh58@gmail.com");
        Employee employeeUpdated = employeeRepository.save(employee);

        //Verify
        System.out.println(employeeUpdated);
        Assertions.assertEquals("thanesh58@gmail.com", employeeUpdated.getEmail(), "Employee email mismatch");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {

        //Action
        int id = 1;
        employeeRepository.deleteById(id);
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        //Verify
        Assertions.assertTrue(employeeOptional.isEmpty());
    }

}