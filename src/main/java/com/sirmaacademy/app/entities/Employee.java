package com.sirmaacademy.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Employee {

    @JsonProperty("Id")
    private int id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("StartDate")
    private LocalDate startDate;
    @JsonProperty("EndDate")
    private LocalDate endDate;
    @JsonProperty("Department")
    private Department department;
    @JsonProperty("Role")
    private String role;
    @JsonProperty("Salary")
    private double salary;

    private boolean doesWork;

    public Employee() {
    }

    public Employee(int id, String name, Department department, String role, double salary) {
        this.id = id;
        this.name = name;
        this.startDate = LocalDate.now();
        this.endDate = null;
        this.department = department;
        this.role = role;
        this.salary = salary;
        this.doesWork = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isDoesWork() {
        return doesWork;
    }

    public void setDoesWork(boolean doesWork) {
        this.doesWork = doesWork;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", department=" + department +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                '}';
    }

    public void fire() {
        this.endDate = LocalDate.now();
        this.doesWork = false;

    }
}
