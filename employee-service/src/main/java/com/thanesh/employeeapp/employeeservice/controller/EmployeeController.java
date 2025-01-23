package com.thanesh.employeeapp.employeeservice.controller;

import com.thanesh.employeeapp.employeeservice.dto.EmployeeDTO;
import com.thanesh.employeeapp.employeeservice.response.ServerResponse;
import com.thanesh.employeeapp.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.GET_EMPLOYEE_BY_ID;
import static com.thanesh.employeeapp.employeeservice.constant.AppConstant.URL_PATH;


@EnableFeignClients
@Tag(name = "Employee App", description = "This is just POC project for micro-service application, here we can perform employee crud related activity.")
@RestController
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;


    @Operation(summary = "Fetch employee", description = "Fetch employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched employee!!!"),
            @ApiResponse(responseCode = "400", description = "Employee NotFound"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error!")
    })
    @GetMapping(GET_EMPLOYEE_BY_ID)
    public ResponseEntity<ServerResponse> getEmployeeById(@PathVariable("id") Integer id) {
        log.info("Request received to fetch Employee id = " + id);
        ResponseEntity<ServerResponse> responseEntity;
        try {
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
            log.debug("getEmployeeById::employeeDTO = " + employeeDTO);
            if (employeeDTO != null) {
                log.info("Employee with id = " + id + " successfully fetched");
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(new ServerResponse(HttpStatus.OK.value() + "", "Employee with id = " + id + " successfully fetched", employeeDTO, HttpStatus.OK.name()));
            } else {
                log.info("Unable to find Data for Employee id = " + id);
                responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ServerResponse(HttpStatus.NOT_FOUND.value() + "", "Employee with id = " + id + " not be found", null, HttpStatus.NOT_FOUND.name()));
            }
            return responseEntity;
        } catch (Exception ex) {
            log.error("Something went wrong, Unable to fetch employee for given id = " + id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                            "Something went wrong, Unable to fetch employee for given id = " + id,
                            null, HttpStatus.INTERNAL_SERVER_ERROR.name()));
        }
    }

    @GetMapping(URL_PATH)
    ResponseEntity<ServerResponse> getAllEmployees() {
        try {
            List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
            log.info("All Employee are successfully fetched!!");
            log.debug("employeeDTOS = " + employeeDTOS);
            return ResponseEntity.ok(new ServerResponse(HttpStatus.OK.value() + "", "All Employee are successfully fetched!!", employeeDTOS, HttpStatus.OK.name()));
        } catch (Exception ex) {
            log.error("Something went wrong, Unable to fetch all employee!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                                    "Something went wrong, Unable to fetch all employee",
                                    null, HttpStatus.INTERNAL_SERVER_ERROR.name()
                            )
                    );
        }
    }
}
