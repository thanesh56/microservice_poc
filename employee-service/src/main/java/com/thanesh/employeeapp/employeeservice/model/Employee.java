package com.thanesh.employeeapp.employeeservice.model;

import jakarta.persistence.*;



@Entity
@Table(name = "employee") //Note: JPA automatically match table and column if it is same as class and there property
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;

    @Column(name= "name")
    private String name;

    @Column(name= "email")
    private String email;

    @Column(name= "bloodgroup")
    private String bloodGroup;

    public Employee(Integer id, String name, String email, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bloodGroup = bloodGroup;
    }

    public Employee() {
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
