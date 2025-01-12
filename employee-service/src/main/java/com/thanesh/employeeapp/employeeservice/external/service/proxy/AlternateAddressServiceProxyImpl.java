package com.thanesh.employeeapp.employeeservice.external.service.proxy;

import com.thanesh.employeeapp.employeeservice.reponse.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@EnableFeignClients(basePackageClasses = AlternateAddressServiceProxy.class)
@ComponentScan(basePackageClasses = AlternateAddressServiceProxy.class)
@Component
public class AlternateAddressServiceProxyImpl implements AddressServiceProxy {
    private AlternateAddressServiceProxy alternateAddressServiceProxy;

    @Autowired
    public AlternateAddressServiceProxyImpl(AlternateAddressServiceProxy alternateAddressServiceProxy) {
        this.alternateAddressServiceProxy = alternateAddressServiceProxy;
    }

    @Override
    public ResponseEntity<ServerResponse> getAddressByEmployeeId(Integer employeeId) {
        System.out.println("Delegating to the USA Server....");
        return alternateAddressServiceProxy.getAddressByEmployeeId(employeeId);
    }
}
