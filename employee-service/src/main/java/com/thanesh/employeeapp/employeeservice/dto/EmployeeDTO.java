package com.thanesh.employeeapp.employeeservice.dto;

public class EmployeeDTO {
    private Integer id;
    private String name;
    private String email;
    private String bloodGroup;
    private Address address;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String name, String email, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bloodGroup = bloodGroup;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", address=" + address +
                '}';
    }
}
