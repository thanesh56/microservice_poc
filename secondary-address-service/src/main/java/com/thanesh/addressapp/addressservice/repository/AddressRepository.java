package com.thanesh.addressapp.addressservice.repository;

import com.thanesh.addressapp.addressservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    //address based on employee id
    //select ea.id, ea.lane_1, ea.lane_2, ea.city, ea.state, ea.zip from address ea join employee e on e.id = ea.employee_id where employee_id = 1;
    @Query(nativeQuery = true, value = "select ea.id, ea.lane_1, ea.lane_2, ea.city, ea.state, ea.zip from address ea join employee e on e.id = ea.employee_id where employee_id=:employeeId")
    Address findAddressByEmployeeId(@Param("employeeId") Integer employeeId);
}
