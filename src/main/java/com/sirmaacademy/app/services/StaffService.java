package com.sirmaacademy.app.services;

import com.sirmaacademy.app.datasource.Constants;
import com.sirmaacademy.app.datasource.FileReader;
import com.sirmaacademy.app.datasource.FileWriter;
import com.sirmaacademy.app.entities.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffService implements Service<Employee> {

    private  FileReader reader;
    private  FileWriter writer;
    private List<Employee> employeeList;

    public StaffService(FileReader reader, FileWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.employeeList = new ArrayList<>();
        read(Constants.EMPLOYEES_FILE_PATH);

    }

    @Override
    public void read(String filepath) {
        this.employeeList = reader.readEmployeeList(filepath);
    }

    @Override
    public void write(String filepath) {
        writer.writeEmployeeList(this.employeeList, filepath);
    }

    @Override
    public List<Employee> getData() {
        return employeeList;
    }

    @Override
    public void appendData(Employee employee) {
        //Adding or hiring employee
        this.employeeList.add(employee);

    }

    @Override
    public void removeData(Employee data) {
        //Firing employee
        data.fire();
        this.employeeList.remove(data);
    }
}
