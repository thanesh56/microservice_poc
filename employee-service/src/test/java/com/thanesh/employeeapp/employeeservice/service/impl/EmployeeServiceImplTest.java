package com.thanesh.employeeapp.employeeservice.service.impl;

import com.thanesh.employeeapp.employeeservice.dto.Address;
import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;
import com.thanesh.employeeapp.employeeservice.external.service.AddressService;
import com.thanesh.employeeapp.employeeservice.model.Employee;
import com.thanesh.employeeapp.employeeservice.response.ServerResponse;
import com.thanesh.employeeapp.employeeservice.repository.EmployeeRepository;
import com.thanesh.employeeapp.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @InjectMocks
    private ModelMapper modelMapperMock;

    @Mock
    private AddressService addressServiceMock;

    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepositoryMock, modelMapperMock, addressServiceMock);
    }

    @Test
    void getEmployeeByIdHappyFlow() {

        // Action
        Integer employeeId = 1;
        Mockito.when(employeeRepositoryMock.findById(anyInt())).thenReturn(getDummyEmployee());
        Address expectedAddress = getDummyAddress().get();
        ResponseEntity<ServerResponse> expectedResponseEntity = ResponseEntity.ok(new ServerResponse(HttpStatus.OK.value() + "", "Address with employeeId = " + employeeId + " successfully fetched!!", expectedAddress, HttpStatus.OK.name()));
        Mockito.when(addressServiceMock.getAddressByEmployeeId(anyInt())).thenReturn(expectedResponseEntity);

        EmployeeDTO actualEmployee = employeeService.getEmployeeById(employeeId);

        // Verify
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(anyInt());
        Assertions.assertEquals(HttpStatusCode.valueOf(HttpStatus.OK.value()), expectedResponseEntity.getStatusCode());
        Employee expectedEmployee = getDummyEmployee().get();
        matchEmployee(expectedEmployee, actualEmployee);
        matchAddress(expectedAddress, actualEmployee.getAddress());
    }

    @Test
    void getEmployeeByIdWhenAddressNotFound() {

        // Action
        Integer employeeId = 1;
        Mockito.when(employeeRepositoryMock.findById(anyInt())).thenReturn(getDummyEmployee());
        ResponseEntity<ServerResponse> expectedResponseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ServerResponse(HttpStatus.NOT_FOUND.value() + "",
                                "Address with employeeId = " + employeeId + " not be found",
                                null, HttpStatus.NOT_FOUND.name()
                        )
                );
        Mockito.when(addressServiceMock.getAddressByEmployeeId(anyInt())).thenReturn(expectedResponseEntity);

        EmployeeDTO actualEmployee = employeeService.getEmployeeById(employeeId);

        // Verify
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(anyInt());
        Assertions.assertEquals(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()), expectedResponseEntity.getStatusCode());
        Employee expectedEmployee = getDummyEmployee().get();
        matchEmployee(expectedEmployee, actualEmployee);
        Assertions.assertNull(actualEmployee.getAddress());
    }

    @Test
    void getEmployeeByIdWhenAddressHostNotAvailable() {

        // Action
        Integer employeeId = 1;
        Mockito.when(employeeRepositoryMock.findById(anyInt())).thenReturn(getDummyEmployee());
        Mockito.when(addressServiceMock.getAddressByEmployeeId(anyInt())).thenReturn(null);

        EmployeeDTO actualEmployee = employeeService.getEmployeeById(employeeId);

        // Verify
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findById(anyInt());
        Employee expectedEmployee = getDummyEmployee().get();
        matchEmployee(expectedEmployee, actualEmployee);
        Assertions.assertNull(actualEmployee.getAddress());
    }

    @Test
    void getEmployeeByIdThrowDBException() {
        // Action
        Mockito.when(employeeRepositoryMock.findById(anyInt())).thenThrow(new RuntimeException("DBException::Something went wrong!"));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(1));

        //Verify
        System.out.println("exception = " + exception);
        Assertions.assertEquals("DBException::Something went wrong!", exception.getMessage(), "Exception message mismatch");
    }

    @Test
    void getAllEmployeesHappyFlow() {

        // Action
        List<Employee> expectedEmployees = getDummyEmployees();
        Mockito.when(employeeRepositoryMock.findAll()).thenReturn(getDummyEmployees());
        List<EmployeeDTO> actualEmployees = employeeService.getAllEmployees();

        // Verify
        Mockito.verify(employeeRepositoryMock, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedEmployees.size(), actualEmployees.size(), "Employee count mismatch");
    }

    @Test
    void getAllEmployeesThrowDBException() {

        // Action
        Mockito.when(employeeRepositoryMock.findAll()).thenThrow(new RuntimeException("DBException::Something went wrong!"));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> employeeService.getAllEmployees());

        //Verify
        System.out.println("exception = " + exception);
        Assertions.assertEquals("DBException::Something went wrong!", exception.getMessage(), "Exception message mismatch");
    }


    private Optional<Employee> getDummyEmployee() {
        return Optional.of(new Employee(1, "Thanesh", "thanesh56@gmail.com", "A+"));
    }

    private Optional<Address> getDummyAddress() {
        return Optional.of(new Address(101, "Bengaluru", "Karnataka", "560029", "BTM Layout 2nd stage", "BTM Layout 8th Main"));
    }

    private List<Employee> getDummyEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Thanesh", "thanesh56@gmail.com", "A+"));
        employees.add(new Employee(2, "Ratan", "ratan@gmail.com", "B+"));
        employees.add(new Employee(3, "Ravi", "ravi.prakash.singh@gmail.com", "O-"));
        employees.add(new Employee(4, "Onkar", "ro@gmail.com", "O+"));
        employees.add(new Employee(5, "Mahesh", "mahesh.007@gmail.com", "B-"));
        employees.add(new Employee(6, "Shreyansh", "shreyansh.69@gmail.com", "A-"));
        return employees;
    }

    private void matchEmployee(Employee expectedEmployee, EmployeeDTO actualEmployee) {
        Assertions.assertEquals(expectedEmployee.getId(), actualEmployee.getId(), "Employee_Id mismatch");
        Assertions.assertEquals(expectedEmployee.getName(), actualEmployee.getName(), "Name mismatch");
        Assertions.assertEquals(expectedEmployee.getEmail(), actualEmployee.getEmail(), "Email mismatch");
        Assertions.assertEquals(expectedEmployee.getBloodGroup(), actualEmployee.getBloodGroup(), "BloodGroup mismatch");
    }

    private void matchAddress(Address expectedAddress, Address actualAddress) {
        Assertions.assertEquals(expectedAddress.getId(), actualAddress.getId(), "Address_Id mismatch");
        Assertions.assertEquals(expectedAddress.getCity(), actualAddress.getCity(), "City mismatch");
        Assertions.assertEquals(expectedAddress.getState(), actualAddress.getState(), "State mismatch");
        Assertions.assertEquals(expectedAddress.getZip(), actualAddress.getZip(), "ZipCode mismatch");
        Assertions.assertEquals(expectedAddress.getLane1(), actualAddress.getLane1(), "Lane1 mismatch");
        Assertions.assertEquals(expectedAddress.getLane2(), actualAddress.getLane2(), "Lane2 mismatch");
    }
}