package com.thanesh.addressapp.addressservice.service.impl;

import com.thanesh.addressapp.addressservice.dto.AddressDTO;
import com.thanesh.addressapp.addressservice.model.Address;
import com.thanesh.addressapp.addressservice.repository.AddressRepository;
import com.thanesh.addressapp.addressservice.service.AddressService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AddressDTO getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        AddressDTO addressDTO = null;
        if (optionalAddress.isPresent()) {
            addressDTO = modelMapper.map(optionalAddress.get(), AddressDTO.class);
        }
        log.info("getAddressById:: Id = {}, Address = {}", id, addressDTO);
        return addressDTO;
    }

    @Override
    public AddressDTO getAddressByEmployeeId(Integer employeeId) {
        Address address = addressRepository.findAddressByEmployeeId(employeeId);
        AddressDTO addressDTO = null;
        if (address != null) {
            addressDTO = modelMapper.map(address, AddressDTO.class);
        }
        log.info("getAddressByEmployeeId::EmployeeId = {}, Address = {}", employeeId, addressDTO);
        return addressDTO;
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addressList = addressRepository.findAll();
        log.info("getAddresses::fetching all address");
        return addressList.stream().map(a -> modelMapper.map(a, AddressDTO.class)).toList();
    }
}
