package com.thanesh.employeeapp.employeeservice.external.service.proxy;

import com.thanesh.employeeapp.employeeservice.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@EnableFeignClients(basePackageClasses = AlternateAddressServiceProxy.class)
@ComponentScan(basePackageClasses = AlternateAddressServiceProxy.class)
@Component
public class AlternateAddressServiceProxyImpl implements AddressServiceProxy {
    private static final Logger log = LoggerFactory.getLogger(AlternateAddressServiceProxyImpl.class);
    private AlternateAddressServiceProxy alternateAddressServiceProxy;

    @Autowired
    public AlternateAddressServiceProxyImpl(AlternateAddressServiceProxy alternateAddressServiceProxy) {
        this.alternateAddressServiceProxy = alternateAddressServiceProxy;
    }

    @Override
    public ResponseEntity<ServerResponse> getAddressByEmployeeId(Integer employeeId) {
        log.info("Delegating to the USA Server....");
        return alternateAddressServiceProxy.getAddressByEmployeeId(employeeId);
    }
}
