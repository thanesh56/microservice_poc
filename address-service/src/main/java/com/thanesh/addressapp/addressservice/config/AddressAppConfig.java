package com.thanesh.addressapp.addressservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressAppConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
