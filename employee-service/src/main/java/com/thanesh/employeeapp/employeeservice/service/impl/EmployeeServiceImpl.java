package com.thanesh.employeeapp.employeeservice.service.impl;

import com.thanesh.employeeapp.employeeservice.dto.Address;
import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;
import com.thanesh.employeeapp.employeeservice.external.service.AddressService;
import com.thanesh.employeeapp.employeeservice.model.Employee;
import com.thanesh.employeeapp.employeeservice.response.ServerResponse;
import com.thanesh.employeeapp.employeeservice.repository.EmployeeRepository;
import com.thanesh.employeeapp.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
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
        log.info("EmployeeServiceImpl::getEmployeeById started");
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        EmployeeDTO employeeDTO = null;
        if (optionalEmployee.isPresent()) {
            log.info("EmployeeServiceImpl::optionalEmployee: " + optionalEmployee.get());
            employeeDTO = modelMapper.map(optionalEmployee.get(), EmployeeDTO.class);

            try {
                //call address microservice
                ServerResponse serverResponseWithAddress = addressService.getAddressByEmployeeId(id).getBody();
                log.info("serverResponseWithAddress = " + serverResponseWithAddress);
                if (HttpStatus.OK.name().equals(serverResponseWithAddress.getStatus())) {
                    Address address = modelMapper.map(serverResponseWithAddress.getData(), Address.class);
                    log.debug("address = " + address);
                    employeeDTO.setAddress(address);
                    log.debug("employeeDTO = " + employeeDTO);
                }
            } catch (Exception ex) {
                log.error("Something went wrong during fetching Employee Address details", ex);
            }
        }
        log.info("EmployeeServiceImpl::getEmployeeById ended!!!");
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("EmployeeServiceImpl::getAllEmployees started");
        List<Employee> employeeList = employeeRepository.findAll();
        log.info("EmployeeServiceImpl::getAllEmployees, employeeList = " + employeeList);
        return employeeList.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).toList();
    }
}
