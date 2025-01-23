package com.thanesh.employeeapp.employeeservice.controller;

import com.thanesh.employeeapp.employeeservice.dto.Address;
import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;
import com.thanesh.employeeapp.employeeservice.service.EmployeeService;
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

import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.GET_EMPLOYEE_BY_ID;
import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.URL_PATH;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeServiceMock;

    @Test
    void getEmployeeByIdHappyFlow() throws Exception {

        // Action
        int id = 1;
        Mockito.when(employeeServiceMock.getEmployeeById(anyInt())).thenReturn(getDummyEmployee());
        ResultActions response = mockMvc.perform(get(GET_EMPLOYEE_BY_ID, id));

        // Verify
        Mockito.verify(employeeServiceMock, Mockito.times((1))).getEmployeeById(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(id + "")));
    }

    @Test
    void getEmployeeByInvalidId() throws Exception {

        // Action
        int id = 10;
        Mockito.when(employeeServiceMock.getEmployeeById(anyInt())).thenReturn(null);
        ResultActions response = mockMvc.perform(get(GET_EMPLOYEE_BY_ID, id));

        // Verify
        Mockito.verify(employeeServiceMock, Mockito.times((1))).getEmployeeById(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().string(containsString("Employee with id = " + id + " not be found")));

    }

    @Test
    void getEmployeeByIdThrowsException() throws Exception {

        // Action
        int id = 1;
        Mockito.when(employeeServiceMock.getEmployeeById(anyInt())).thenThrow(NullPointerException.class);
        ResultActions response = mockMvc.perform(get(GET_EMPLOYEE_BY_ID, id));

        // Verify
        Mockito.verify(employeeServiceMock, Mockito.times((1))).getEmployeeById(anyInt());
        System.out.println("response = " + response);
        response.andExpect(status().isInternalServerError())
                .andDo(print())
                .andExpect(content().string(containsString("Something went wrong, Unable to fetch employee for given id = " + id)));
    }

    @Test
    void getAllEmployeesHappyFlow() throws Exception {

        // Action
        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(getDummyEmployees());
        ResultActions response = mockMvc.perform(get(URL_PATH));

        // Verify
        Mockito.verify(employeeServiceMock, Mockito.times((1))).getAllEmployees();
        System.out.println("response = " + response);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("Thanesh")))
                .andExpect(content().string(containsString("Ravi")))
                .andExpect(content().string(containsString("Ratan")));
    }

    @Test
    void getAllEmployeesEmptyListOfEmployeeScenario() throws Exception {

        // Action
        List<EmployeeDTO> emptyEmployee = Collections.emptyList();
        Mockito.when(employeeServiceMock.getAllEmployees()).thenReturn(emptyEmployee);
        ResultActions response = mockMvc.perform(get(URL_PATH));

        // Verify
        Mockito.verify(employeeServiceMock, Mockito.times((1))).getAllEmployees();
        System.out.println("response = " + response);
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    void getAllEmployeesThrowsException() throws Exception {

        // Action
        Mockito.when(employeeServiceMock.getAllEmployees()).thenThrow(NullPointerException.class);
        ResultActions response = mockMvc.perform(get(URL_PATH));

        // Verify
        Mockito.verify(employeeServiceMock, Mockito.times((1))).getAllEmployees();
        System.out.println("response = " + response);
        response.andExpect(status().isInternalServerError())
                .andDo(print())
                .andExpect(content().string(containsString("Something went wrong, Unable to fetch all employee")));
    }

    private EmployeeDTO getDummyEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Thanesh", "thanesh56@gmail.com", "A+");
        employeeDTO.setAddress(getDummyAddress());
        return employeeDTO;
    }

    private EmployeeDTO getDummyEmployee2() {
        EmployeeDTO employeeDTO = new EmployeeDTO(2, "Ratan", "ratan@gmail.com", "B+");
        employeeDTO.setAddress(getDummyAddress2());
        return employeeDTO;
    }

    private EmployeeDTO getDummyEmployee3() {
        EmployeeDTO employeeDTO = new EmployeeDTO(3, "Ravi", "ravi.prakash.singh@gmail.com", "O-");
        employeeDTO.setAddress(getDummyAddress3());
        return employeeDTO;
    }

    private Address getDummyAddress() {
        return new Address(101, "Bengaluru", "Karnataka", "560029", "BTM Layout 2nd stage", "BTM Layout 8th Main");
    }

    private Address getDummyAddress2() {
        return new Address(102, "Hyderabad", "Telangana", "500075", "Stanza Living Memphis House", "Coliving PG in Q City, Memphis house, Unnamed Road, Padma Sri Gardens Layout, Gowlidoddy");
    }

    private Address getDummyAddress3() {
        return new Address(103, "Durg", "Chhattisgarh", "491001", "Kolihapuri, Balod Road, near CSIT Durg", "CSIT Durg");
    }

    private List<EmployeeDTO> getDummyEmployees() {
        List<EmployeeDTO> employees = new ArrayList<>();
        employees.add(getDummyEmployee());
        employees.add(getDummyEmployee2());
        employees.add(getDummyEmployee3());
        return employees;
    }
}