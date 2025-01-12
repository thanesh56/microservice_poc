package com.thanesh.employeeapp.employeeservice.service.impl;

import com.thanesh.employeeapp.employeeservice.dto.Address;
import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;
import com.thanesh.employeeapp.employeeservice.external.service.AddressService;
import com.thanesh.employeeapp.employeeservice.model.Employee;
import com.thanesh.employeeapp.employeeservice.reponse.ServerResponse;
import com.thanesh.employeeapp.employeeservice.repository.EmployeeRepository;
import com.thanesh.employeeapp.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    AddressService addressService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper, AddressService addressService) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.addressService = addressService;
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        EmployeeDTO employeeDTO = null;
        if (optionalEmployee.isPresent()) {
            System.out.println("EmployeeServiceImpl::optionalEmployee: " + optionalEmployee.get());
            System.out.println("optionalEmployee: " + optionalEmployee.get());
            employeeDTO = modelMapper.map(optionalEmployee.get(), EmployeeDTO.class);

            try {
                //call address microservice
                ServerResponse serverResponseWithAddress = addressService.getAddressByEmployeeId(id).getBody();
                System.out.println("serverResponseWithAddress = " + serverResponseWithAddress);
                if (HttpStatus.OK.name().equals(serverResponseWithAddress.getStatus())) {
                    Address address = modelMapper.map(serverResponseWithAddress.getData(), Address.class);
                    System.out.println("address = " + address);
                    employeeDTO.setAddress(address);
                    System.out.println("employeeDTO = " + employeeDTO);
                }
            } catch (Exception ex) {
                System.out.println("ex = " + ex);
            }
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
