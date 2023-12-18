package com.sirmaacademy.app.services;

import java.util.List;

public interface Service<T> {
    void read(String filepath);
    void write(String filepath);
    List<T> getData();
    void appendData(T data);
    void removeData(T data);
}
