package com.thanesh.employeeapp.employeeservice.external.service;

import com.thanesh.employeeapp.employeeservice.dto.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ADDRESS-SERVICE", path = "/address-app/api")
public interface AddressClient {
    @GetMapping("/addresses/{employeeId}")
    Address getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId);
}
