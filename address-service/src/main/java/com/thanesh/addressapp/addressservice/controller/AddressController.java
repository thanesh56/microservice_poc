package com.thanesh.addressapp.addressservice.controller;

import com.thanesh.addressapp.addressservice.dto.AddressDTO;
import com.thanesh.addressapp.addressservice.response.ServerResponse;
import com.thanesh.addressapp.addressservice.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.thanesh.addressapp.addressservice.constant.AppConstant.GET_ADDRESS_BY_EMPLOYEE_ID;
import static com.thanesh.addressapp.addressservice.constant.AppConstant.GET_ADDRESS_BY_ID;
import static com.thanesh.addressapp.addressservice.constant.AppConstant.ULR_PATH_ADDRESSES;

@Tag(name = "Address App", description = "This is a simple POC for Micro-service Architecture")
@RestController
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Fetch Address", description = "Fetch Address by employee id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched Address!!!"),
            @ApiResponse(responseCode = "400", description = "Address NotFound"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error!")
    })
    @GetMapping(GET_ADDRESS_BY_EMPLOYEE_ID)
    public ResponseEntity<ServerResponse> getAddressByEmployeeId(@PathVariable("id") Integer employeeId) {
        log.info("Received Request to fetch Address of employeeId = {}", employeeId);
        ResponseEntity<ServerResponse> responseEntity;
        try {
            AddressDTO addressDTO = addressService.getAddressByEmployeeId(employeeId);
            log.debug("getAddressByEmployeeId::addressDTO = " + addressDTO);
            if (addressDTO != null) {
                log.info("Address with employeeId = {} successfully fetched!!", employeeId);
                responseEntity = ResponseEntity.ok(new ServerResponse(HttpStatus.OK.value() + "", "Address with employeeId = " + employeeId + " successfully fetched!!", addressDTO, HttpStatus.OK.name()));
            } else {
                log.error("Unable to fetch address of given employeeId = {}, addressDTO = {}", employeeId, null);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ServerResponse(HttpStatus.NOT_FOUND.value() + "", "Address with employeeId = " + employeeId + " not be found", null, HttpStatus.NOT_FOUND.name()));
            }
            return responseEntity;
        } catch (Exception ex) {
            log.error("Something went wrong, Unable to fetch address for given employee id = " + employeeId, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                            "Something went wrong, Unable to fetch address for given employeeId = " + employeeId,
                            null, HttpStatus.INTERNAL_SERVER_ERROR.name()
                            )
                    );
        }
    }

    /*@GetMapping(GET_ADDRESS_BY_EMPLOYEE_ID)
    public AddressDTO getAddressByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
        System.out.println("employeeId = " + employeeId);
        AddressDTO addressDTO = addressService.getAddressByEmployeeId(employeeId);
        System.out.println("Received Request to Address of employeeId = " + employeeId);
        System.out.println("addressDTO = " + addressDTO);
        return addressDTO;
    }*/

    @GetMapping(GET_ADDRESS_BY_ID)
    public ResponseEntity<ServerResponse> getAddressById(@PathVariable("id") Integer id) {
        log.info("Received Request to get address, where address id = " + id);
        ResponseEntity<ServerResponse> responseEntity;
        try {
            AddressDTO addressDTO = addressService.getAddressById(id);
            log.info("getAddressById::addressDTO = " + addressDTO);
            if (addressDTO != null) {
                log.info("Address with id = {}  successfully fetched", id);
                responseEntity = ResponseEntity.ok(new ServerResponse(HttpStatus.OK.value() + "", "Address with id = " + id + " successfully fetched", addressDTO, HttpStatus.OK.name()));
            } else {
                log.error("Unable to fetch address of given address id = {}, addressDTO = {}", id, null);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ServerResponse(HttpStatus.NOT_FOUND.value() + "",
                        "Address with id = " + id + " not be found", null, HttpStatus.NOT_FOUND.name()));
            }
            return responseEntity;
        } catch (Exception ex) {
            log.error("Something went wrong, Unable to fetch address for given id = " + id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                    "Something went wrong, Unable to fetch address for given id = " + id,
                    null, HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }

    @GetMapping(ULR_PATH_ADDRESSES)
    public ResponseEntity<ServerResponse> getAllAddress() {
        try {
            List<AddressDTO> addressDTOS = addressService.getAddresses();
            log.info("All Address are successfully fetched!!");
            log.debug("addressDTOS = " + addressDTOS);
            return ResponseEntity.ok(new ServerResponse(HttpStatus.OK.value() + "", "All Address are successfully fetched!!", addressDTOS, HttpStatus.OK.name()));
        } catch (Exception ex) {
            log.error("Something went wrong, Unable to fetch all address!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", "Something went wrong, Unable to fetch all address", null, HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }
}
