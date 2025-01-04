package com.thanesh.addressapp.addressservice.controller;

import com.thanesh.addressapp.addressservice.dto.AddressDTO;
import com.thanesh.addressapp.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.thanesh.addressapp.addressservice.constant.AppConstant.GET_ADDRESS_BY_EMPLOYEE_ID;
import static com.thanesh.addressapp.addressservice.constant.AppConstant.ULR_PATH_ADDRESSES;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping(GET_ADDRESS_BY_EMPLOYEE_ID)
    public AddressDTO getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
        AddressDTO addressDTO = addressService.getAddressByEmployeeId(employeeId);
        System.out.println("Received Request to Address of employeeId = " + employeeId);
        System.out.println("addressDTO = " + addressDTO);

        return addressDTO;
    }

   /* @GetMapping(GET_ADDRESS_BY_ID)
    public ResponseEntity<ServerResponse> getAddress(@PathVariable("id") Integer id) {
        AddressDTO addressDTO = addressService.getAddressById(id);
        System.out.println("Received Request to Address id = " + id);
        System.out.println("addressDTO = " + addressDTO);
        ResponseEntity<ServerResponse> responseEntity;
        if (addressDTO != null) {
            responseEntity = new ResponseEntity<>(new ServerResponse(HttpStatus.OK.value() + "", "Address with id = " + id + " successfully", addressDTO, HttpStatus.OK.name()), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(new ServerResponse(HttpStatus.NOT_FOUND.value() + "", "Address with id = " + id + " not be found", null, HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }*/

    @GetMapping(ULR_PATH_ADDRESSES)
    public List<AddressDTO> getAllEmployees() {
        List<AddressDTO> addressDTOS = addressService.getAddresses();
        System.out.println("addressDTOS = " + addressDTOS);
        return addressDTOS;
    }
}
