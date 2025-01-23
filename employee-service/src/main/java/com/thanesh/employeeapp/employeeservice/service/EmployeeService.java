package com.thanesh.employeeapp.employeeservice.service;

import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO getEmployeeById(Integer id);

    List<EmployeeDTO> getAllEmployees();
}
