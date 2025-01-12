package com.thanesh.employeeapp.employeeservice.external.service.proxy;

import com.thanesh.employeeapp.employeeservice.external.service.AddressService;
import org.springframework.cloud.openfeign.FeignClient;

import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.*;

@FeignClient(name = ALTERNATE_ADDRESS_SERVICE, path = ALTERNATE_ADDRESS_BASE_URL, primary = false)
public interface AlternateAddressServiceProxy extends AddressService {

}
