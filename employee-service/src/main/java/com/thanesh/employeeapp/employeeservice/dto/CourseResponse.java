package com.thanesh.employeeapp.employeeservice.dto;

import java.util.Objects;

public class CourseResponse {
    private Integer id;
    private String name;
    private String author;
    private String edition;
    private Double price;

    public CourseResponse() {
    }

    public CourseResponse(Integer id, String name, String author, String edition, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CourseResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseResponse that = (CourseResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(author, that.author) && Objects.equals(edition, that.edition) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, edition, price);
    }
}
