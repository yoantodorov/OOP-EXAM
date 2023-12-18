package com.sirmaacademy.app.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sirmaacademy.app.entities.Employee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {

    public List<Employee> readEmployeeList(String filepath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            File file = new File(filepath);

            Employee[] employees = objectMapper.readValue(file, Employee[].class);
            return new ArrayList<>(Arrays.asList(employees));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
