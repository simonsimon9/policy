package org.example.app.model;

import java.math.BigDecimal;

public class Users {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private Integer phoneNumber;
    private String email;
    private BigDecimal salary;
    private Integer panNumber;
    private EmployerType employeeType;
    private String employerName;
    public Users(){

    }
    public Users(String firstName, String lastName, String dateOfBirth, String address, Integer phoneNumber, String email, BigDecimal salary, Integer panNumber, EmployerType employeeType, String employerName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.panNumber = panNumber;
        this.employeeType = employeeType;
        this.employerName = employerName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(Integer panNumber) {
        this.panNumber = panNumber;
    }

    public EmployerType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployerType employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }
}
