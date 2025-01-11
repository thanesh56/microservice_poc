package com.thanesh.addressapp.addressservice.service;

import com.thanesh.addressapp.addressservice.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    public AddressDTO getAddressById(Integer id);

    public AddressDTO getAddressByEmployeeId(Integer employeeId);

    public List<AddressDTO> getAddresses();
}
