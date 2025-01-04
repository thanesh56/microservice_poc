package com.thanesh.addressapp.addressservice.service.impl;

import com.thanesh.addressapp.addressservice.dto.AddressDTO;
import com.thanesh.addressapp.addressservice.model.Address;
import com.thanesh.addressapp.addressservice.repository.AddressRepository;
import com.thanesh.addressapp.addressservice.service.AddressService;
import org.modelmapper.ModelMapper;
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
        return addressDTO;
    }

    @Override
    public AddressDTO getAddressByEmployeeId(Integer employeeId) {
        return modelMapper.map(addressRepository.findAddressByEmployeeId(employeeId), AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addressList = addressRepository.findAll();
        return addressList.stream().map(a -> modelMapper.map(a, AddressDTO.class)).toList();
    }
}
