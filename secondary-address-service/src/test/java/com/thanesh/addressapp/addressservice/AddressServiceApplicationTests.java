package com.thanesh.addressapp.addressservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AddressServiceApplicationTests {

    @Test
    public void testAddressApplication() {
        Integer expectedValue = 10;
        Integer actualValue = 10;
        Assertions.assertEquals(expectedValue, actualValue, "value mismatch");
    }

}
