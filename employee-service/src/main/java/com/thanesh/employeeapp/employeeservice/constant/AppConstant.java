package com.thanesh.employeeapp.employeeservice.constant;

public interface AppConstant {

    String URL_PATH = "/employees";
    String GET_EMPLOYEE_BY_ID = URL_PATH + "/{id}";
    String GET_ADDRESS_BY_EMPLOYEE_ID = "/addresses/{employeeId}";
    String ADDRESS_SERVICE = "ADDRESS-SERVICE";
    String ADDRESS_BASE_URL = "/address-app/api";
    String ALTERNATE_ADDRESS_SERVICE = "SECONDARY-ADDRESS-SERVICE";
    String ALTERNATE_ADDRESS_BASE_URL = "/secondary-address-app/api";
}
