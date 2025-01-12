package com.thanesh.employeeapp.employeeservice.external.service.proxy;

import com.thanesh.employeeapp.employeeservice.external.service.AddressService;
import org.springframework.cloud.openfeign.FeignClient;

import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.ADDRESS_BASE_URL;
import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.ALTERNATE_ADDRESS_SERVICE;

//Secondary Server, call will fall here if primary is unavailable
@FeignClient(name = ALTERNATE_ADDRESS_SERVICE, path = ADDRESS_BASE_URL, primary = false)
public interface AlternateAddressServiceProxy extends AddressService {

}
