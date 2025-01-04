package com.thanesh.employeeapp.employeeservice.controller;

import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;
import com.thanesh.employeeapp.employeeservice.reponse.ServerResponse;
import com.thanesh.employeeapp.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.GET_EMPLOYEE_BY_ID;
import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.URL_PATH;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(GET_EMPLOYEE_BY_ID)
    public ResponseEntity<ServerResponse> getEmployeeById(@PathVariable("id") Integer id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        System.out.println("Received Request to Employee id = " + id);
        System.out.println("employeeDTO = " + employeeDTO);
        ResponseEntity<ServerResponse> responseEntity;
        if (employeeDTO != null) {
            //responseEntity = new ResponseEntity<>(new ServerResponse(HttpStatus.OK.value() + "", "Employee with id = " + id + " successfully fetched", employeeDTO, HttpStatus.OK.name()), HttpStatus.OK);
            //or both are same
            responseEntity =  ResponseEntity.status(HttpStatus.OK).body(new ServerResponse(HttpStatus.OK.value() + "", "Employee with id = " + id + " successfully fetched", employeeDTO, HttpStatus.OK.name()));
        } else {
            //responseEntity = new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND.value() + "", "Employee with id = " + id + " not be found", null, HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
            //or
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ServerResponse(HttpStatus.NOT_FOUND.value() + "", "Employee with id = " + id + " not be found", null, HttpStatus.NOT_FOUND.name()));
        }

        return responseEntity;
    }

    @GetMapping(URL_PATH)
    List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOS = employeeService.getEmployees();
        System.out.println("employeeDTOS = " + employeeDTOS);
        return employeeDTOS;
    }
}
