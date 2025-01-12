package com.thanesh.employeeapp.employeeservice.external.service.proxy;

import com.thanesh.employeeapp.employeeservice.external.service.AddressService;
import org.springframework.cloud.openfeign.FeignClient;

import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.ADDRESS_BASE_URL;
import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.ADDRESS_SERVICE;


//Primary server
@FeignClient(name = ADDRESS_SERVICE, path = ADDRESS_BASE_URL, fallback = AlternateAddressServiceProxyImpl.class)
public interface AddressServiceProxy extends AddressService {

}

