package com.thanesh.employeeapp.employeeservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeAppConfig {

    @Bean
    public ModelMapper getModelMapper(){
       return new ModelMapper();
    }
}
