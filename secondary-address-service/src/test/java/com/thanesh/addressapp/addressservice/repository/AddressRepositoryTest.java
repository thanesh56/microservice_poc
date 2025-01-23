package com.thanesh.addressapp.addressservice.repository;

import com.thanesh.addressapp.addressservice.model.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Test 1:Save Address Test")
    @Order(1)
    @Rollback(value = false)
    public void saveAddressTest() {

        //Action
        Address address = new Address(1, "Bengaluru", "Karnataka", "560029",
                "BTM Layout 1st Stage", "BTM 8th Main");

        addressRepository.save(address);

        //Verify
        System.out.println(address);
        Assertions.assertTrue(address.getId() > 0, "Address Id mismatch");
    }

    @Test
    @Order(2)
    public void getAddressTest() {

        //Action
        int id = 1;
        Optional<Address> addressOptional = addressRepository.findById(id);

        //Verify
        Assertions.assertTrue(addressOptional.isPresent());
        System.out.println(addressOptional.get());
        Assertions.assertEquals(id, addressOptional.get().getId(), "Address Id mismatch");
    }

    @Test
    @Order(3)
    public void getListOfAddressTest() {

        //Action
        List<Address> addresses = addressRepository.findAll();

        //Verify
        System.out.println(addresses);
        Assertions.assertTrue(addresses.size() > 0, "Address count mismatch");

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAddressTest() {

        //Action
        int id = 1;
        Address address = addressRepository.findById(id).get();
        address.setZip("560103");
        Address addressUpdated = addressRepository.save(address);

        //Verify
        System.out.println(addressUpdated);
        Assertions.assertEquals("560103", addressUpdated.getZip(), "Address ZipCode mismatch");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteAddressTest() {

        //Action
        int id = 1;
        addressRepository.deleteById(id);
        Optional<Address> addressOptional = addressRepository.findById(id);

        //Verify
        Assertions.assertTrue(addressOptional.isEmpty());
    }
}