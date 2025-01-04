package com.thanesh.addressapp.addressservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "lane_1")
    private String lane1;

    @Column(name = "lane_2")
    private String lane2;

    public Address() {
    }

    public Address(Integer id, String city, String state, String zip, String lane1, String lane2) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.lane1 = lane1;
        this.lane2 = lane2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLane1() {
        return lane1;
    }

    public void setLane1(String lane1) {
        this.lane1 = lane1;
    }

    public String getLane2() {
        return lane2;
    }

    public void setLane2(String lane2) {
        this.lane2 = lane2;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", lane1='" + lane1 + '\'' +
                ", lane2='" + lane2 + '\'' +
                '}';
    }
}
