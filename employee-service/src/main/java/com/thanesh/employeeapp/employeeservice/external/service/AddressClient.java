package com.thanesh.employeeapp.employeeservice.external.service;

import com.thanesh.employeeapp.employeeservice.dto.Address;
import com.thanesh.employeeapp.employeeservice.reponse.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.GET_ADDRESS_BY_EMPLOYEE_ID;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {
    /*@GetMapping(GET_ADDRESS_BY_EMPLOYEE_ID)
    Address getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId);*/

    @GetMapping(GET_ADDRESS_BY_EMPLOYEE_ID)
    ResponseEntity<ServerResponse> getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId);
}
