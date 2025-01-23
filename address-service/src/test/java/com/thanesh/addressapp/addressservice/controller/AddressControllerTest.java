package com.thanesh.addressapp.addressservice.controller;

import com.thanesh.addressapp.addressservice.dto.AddressDTO;
import com.thanesh.addressapp.addressservice.service.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.thanesh.addressapp.addressservice.constant.AppConstant.GET_ADDRESS_BY_EMPLOYEE_ID;
import static com.thanesh.addressapp.addressservice.constant.AppConstant.GET_ADDRESS_BY_ID;
import static com.thanesh.addressapp.addressservice.constant.AppConstant.ULR_PATH_ADDRESSES;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressServiceMock;

    @Test
    void getAddressByEmployeeId() throws Exception {

        // Action
        int expectedEmployeeId = 1;
        AddressDTO expectedAddress = getDummyAddress();
        Mockito.when(addressServiceMock.getAddressByEmployeeId(anyInt())).thenReturn(expectedAddress);
        ResultActions response = mockMvc.perform(get(GET_ADDRESS_BY_EMPLOYEE_ID, expectedEmployeeId));


        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddressByEmployeeId(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(expectedAddress.getId().toString())));
    }

    @Test
    void getAddressByInvalidEmployeeId() throws Exception {

        // Action
        int expectedEmployeeId = 10;
        Mockito.when(addressServiceMock.getAddressByEmployeeId(anyInt())).thenReturn(null);
        ResultActions response = mockMvc.perform(get(GET_ADDRESS_BY_EMPLOYEE_ID, expectedEmployeeId));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddressByEmployeeId(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().string(containsString("Address with employeeId = " + expectedEmployeeId + " not be found")));
    }

    @Test
    void getAddressByEmployeeIdThrowException() throws Exception {

        // Action
        int expectedEmployeeId = 1;
        Mockito.when(addressServiceMock.getAddressByEmployeeId(anyInt())).thenThrow(NullPointerException.class);
        ResultActions response = mockMvc.perform(get(GET_ADDRESS_BY_EMPLOYEE_ID, expectedEmployeeId));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddressByEmployeeId(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isInternalServerError())
                .andDo(print())
                .andExpect(content().string(containsString("Something went wrong, Unable to fetch address for given employeeId = " + expectedEmployeeId)));
    }


    @Test
    void getAddressById() throws Exception {

        // Action
        int id = 1;
        AddressDTO expectedAddress = getDummyAddress();
        Mockito.when(addressServiceMock.getAddressById(anyInt())).thenReturn(expectedAddress);
        ResultActions response = mockMvc.perform(get(GET_ADDRESS_BY_ID, id));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddressById(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(id + "")));
    }

    @Test
    void getAddressByInvalidId() throws Exception {

        // Action
        int id = 1;
        Mockito.when(addressServiceMock.getAddressById(anyInt())).thenReturn(null);
        ResultActions response = mockMvc.perform(get(GET_ADDRESS_BY_ID, id));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddressById(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().string(containsString("Address with id = " + id + " not be found")));
    }

    @Test
    void getAddressByIdThrowException() throws Exception {

        // Action
        int id = 1;
        Mockito.when(addressServiceMock.getAddressById(anyInt())).thenThrow(NullPointerException.class);
        ResultActions response = mockMvc.perform(get(GET_ADDRESS_BY_ID, id));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddressById(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isInternalServerError())
                .andDo(print())
                .andExpect(content().string(containsString("Something went wrong, Unable to fetch address for given id = " + id)));
    }

    @Test
    void getAllAddress() throws Exception {

        // Action
        List<AddressDTO> dummyAddresses = getDummyAddresses();
        Mockito.when(addressServiceMock.getAddresses()).thenReturn(dummyAddresses);
        ResultActions response = mockMvc.perform(get(ULR_PATH_ADDRESSES));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddresses();
        System.out.println("response = " + response);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("BTM Layout 2nd stage")));
    }

    @Test
    void getAllAddressForEmptyListOfAddressScenario() throws Exception {

        // Action
        List<AddressDTO> dummyAddresses = Collections.emptyList();
        Mockito.when(addressServiceMock.getAddresses()).thenReturn(dummyAddresses);
        ResultActions response = mockMvc.perform(get(ULR_PATH_ADDRESSES));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddresses();
        System.out.println("response = " + response);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    void getAllAddressExceptionScenario() throws Exception {

        // Action
        Mockito.when(addressServiceMock.getAddresses()).thenThrow(NullPointerException.class);
        ResultActions response = mockMvc.perform(get(ULR_PATH_ADDRESSES));

        // Verify
        Mockito.verify(addressServiceMock, Mockito.times(1)).getAddresses();
        System.out.println("response = " + response);
        response.andExpect(status().isInternalServerError())
                .andDo(print())
                .andExpect(content().string(containsString("Something went wrong, Unable to fetch all address")));
    }

    private AddressDTO getDummyAddress() {
        return new AddressDTO(101, "Bengaluru", "Karnataka", "560029", "BTM Layout 2nd stage", "BTM Layout 8th Main");
    }

    private List<AddressDTO> getDummyAddresses() {
        List<AddressDTO> addressDTOS = new ArrayList<>();
        addressDTOS.add(new AddressDTO(101, "Bengaluru", "Karnataka", "560029", "BTM Layout 2nd stage", "BTM Layout 8th Main"));
        addressDTOS.add(new AddressDTO(102, "Hyderabad", "Telangana", "500075", "Stanza Living Memphis House", "Coliving PG in Q City, Memphis house, Unnamed Road, Padma Sri Gardens Layout, Gowlidoddy"));
        addressDTOS.add(new AddressDTO(103, "Durg", "Chhattisgarh", "491001", "Kolihapuri, Balod Road, near CSIT Durg", "CSIT Durg"));
        return addressDTOS;
    }
}