package com.sirmaacademy.app.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter {



    public  <T> void writeEmployeeList(List<T> list, String filepath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            objectMapper.writeValue(new File(filepath), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
