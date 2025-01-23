package com.thanesh.addressapp.addressservice.service.impl;

import com.thanesh.addressapp.addressservice.dto.AddressDTO;
import com.thanesh.addressapp.addressservice.model.Address;
import com.thanesh.addressapp.addressservice.repository.AddressRepository;
import com.thanesh.addressapp.addressservice.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepositoryMock;

    @InjectMocks
    private ModelMapper modelMapperMock;

    private AddressService addressService;

    @BeforeEach
    void setUp() {
        addressService = new AddressServiceImpl(addressRepositoryMock, modelMapperMock);
    }

    @Test
    void getAddressByIdHappyFlow() {

        //Action
        Address expectedAddress = getDummyAddress().get();
        Mockito.when(addressRepositoryMock.findById(anyInt())).thenReturn(getDummyAddress());
        AddressDTO actualAddress = addressService.getAddressById(101);

        //Verify
        Mockito.verify(addressRepositoryMock, Mockito.times(1)).findById(anyInt());
        matchAddress(expectedAddress, actualAddress);
    }

    @Test
    void getAddressByInvalidId() {

        //Action
        Mockito.when(addressRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());
        AddressDTO actualAddress = addressService.getAddressById(120);

        //Verify
        Mockito.verify(addressRepositoryMock, Mockito.times(1)).findById(anyInt());
        Assertions.assertNull(actualAddress);
    }

    @Test
    void getAddressByIdThrowDBException() {

        //Action
        Mockito.when(addressRepositoryMock.findById(anyInt())).thenThrow(new RuntimeException("DBException::Something went wrong!"));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> addressService.getAddressById(120));

        //Verify
        System.out.println("exception = " + exception);
        Assertions.assertEquals("DBException::Something went wrong!", exception.getMessage(), "Exception message mismatch");
    }

    @Test
    void getAddressByEmployeeIdHappyFlow() {

        //Action
        Address expectedAddress = getDummyAddress().get();
        Mockito.when(addressRepositoryMock.findAddressByEmployeeId(anyInt())).thenReturn(expectedAddress);
        AddressDTO actualAddress = addressService.getAddressByEmployeeId(1);

        //Verify
        Mockito.verify(addressRepositoryMock, Mockito.times(1)).findAddressByEmployeeId(anyInt());
        matchAddress(expectedAddress, actualAddress);
    }

    @Test
    void getAddressByEmployeeIdWhichIsNotExists() {

        //Action
        Mockito.when(addressRepositoryMock.findAddressByEmployeeId(anyInt())).thenReturn(null);
        AddressDTO actualAddress = addressService.getAddressByEmployeeId(13);

        //Verify
        Mockito.verify(addressRepositoryMock, Mockito.times(1)).findAddressByEmployeeId(anyInt());
        Assertions.assertNull(actualAddress);
    }

    @Test
    void getAddressByEmployeeIdThrowsDBException() {

        //Action
        Mockito.when(addressRepositoryMock.findAddressByEmployeeId(anyInt())).thenThrow(new RuntimeException("DBException::Something went wrong!"));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> addressService.getAddressByEmployeeId(13));

        //Verify
        System.out.println("exception = " + exception);
        Assertions.assertEquals("DBException::Something went wrong!", exception.getMessage(), "Exception message mismatch");
    }

    @Test
    void getAddresses() {

        //Action
        List<Address> expectedAddresses = getDummyAddresses();
        Mockito.when(addressRepositoryMock.findAll()).thenReturn(expectedAddresses);
        List<AddressDTO> actualAddresses = addressService.getAddresses();

        //Verify
        Mockito.verify(addressRepositoryMock, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedAddresses.size(), actualAddresses.size());
    }

    @Test
    void getAddressesItWillReturnEmptyAddress() {

        //Action
        List<Address> expectedAddresses = Collections.emptyList();
        Mockito.when(addressRepositoryMock.findAll()).thenReturn(expectedAddresses);
        List<AddressDTO> actualAddresses = addressService.getAddresses();

        //Verify
        Mockito.verify(addressRepositoryMock, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedAddresses.size(), actualAddresses.size(), "Address mismatch");
        Assertions.assertEquals(0, actualAddresses.size(), "Address count mismatch");
    }

    @Test
    void getAddressesItWillThrowsDBException() {

        //Action
        Mockito.when(addressRepositoryMock.findAll()).thenThrow(new RuntimeException("DBException::Something went wrong!"));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> addressService.getAddresses());

        //Verify
        System.out.println("exception = " + exception);
        Assertions.assertEquals("DBException::Something went wrong!", exception.getMessage(), "Exception message mismatch");
    }

    private List<Address> getDummyAddresses() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(101, "Bengaluru", "Karnataka", "560029", "BTM Layout 2nd stage", "BTM Layout 8th Main"));
        addresses.add(new Address(102, "Hyderabad", "Telangana", "500075", "Stanza Living Memphis House", "Coliving PG in Q City, Memphis house, Unnamed Road, Padma Sri Gardens Layout, Gowlidoddy"));
        addresses.add(new Address(103, "Durg", "Chhattisgarh", "491001", "Kolihapuri, Balod Road, near CSIT Durg", "CSIT Durg"));
        return addresses;
    }


    private Optional<Address> getDummyAddress() {
        return Optional.of(new Address(101, "Bengaluru", "Karnataka", "560029", "BTM Layout 2nd stage", "BTM Layout 8th Main"));
    }

    private void matchAddress(Address expectedAddress, AddressDTO actualAddress) {
        Assertions.assertEquals(expectedAddress.getId(), actualAddress.getId(), "Address_Id mismatch");
        Assertions.assertEquals(expectedAddress.getCity(), actualAddress.getCity(), "City mismatch");
        Assertions.assertEquals(expectedAddress.getState(), actualAddress.getState(), "State mismatch");
        Assertions.assertEquals(expectedAddress.getZip(), actualAddress.getZip(), "ZipCode mismatch");
        Assertions.assertEquals(expectedAddress.getLane1(), actualAddress.getLane1(), "Lane1 mismatch");
        Assertions.assertEquals(expectedAddress.getLane2(), actualAddress.getLane2(), "Lane2 mismatch");
    }
}