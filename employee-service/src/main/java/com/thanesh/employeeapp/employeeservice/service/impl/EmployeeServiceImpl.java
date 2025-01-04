package com.thanesh.employeeapp.employeeservice.service.impl;

import com.thanesh.employeeapp.employeeservice.dto.Address;
import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;
import com.thanesh.employeeapp.employeeservice.external.service.AddressClient;
import com.thanesh.employeeapp.employeeservice.model.Employee;
import com.thanesh.employeeapp.employeeservice.repository.EmployeeRepository;
import com.thanesh.employeeapp.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
     EmployeeRepository employeeRepository;

    @Autowired
     ModelMapper modelMapper;

    @Autowired
    AddressClient addressClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, AddressClient addressClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.addressClient = addressClient;
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        EmployeeDTO employeeDTO = null;
        if (optionalEmployee.isPresent()) {
            System.out.println("EmployeeServiceImpl::optionalEmployee: " + optionalEmployee.get());
            System.out.println("optionalEmployee: " + optionalEmployee.get());
            employeeDTO = modelMapper.map(optionalEmployee.get(), EmployeeDTO.class);

            //call address microservice
            Address address = addressClient.getAddressByEmployeeId(id);
            System.out.println("address = " + address);
            employeeDTO.setAddress(address);
            System.out.println("employeeDTO = " + employeeDTO);

        }
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("EmployeeServiceImpl::employeeList = " + employeeList);
        return employeeList.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).toList();
    }
}
