package com.sirmaacademy.app.datasource;

import java.io.File;

public class Constants {
    public static final String EMPLOYEES_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "employees.json";
    public static final String TEST_FILE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "test.json";
    public static final String WELCOME_MSG = "Welcome to Staff Management System";
    public static final String EMPLOYEE_DETAILS_MSG = "Enter employee data(id,name,department,position,salary) or Cancel:";
    public static final String INVALID_FIELDS_MSG = "Invalid input. Please provide all required fields.";
    public static final String INVALID_DEPARTMENT_MSG = "Invalid department. Provide a valid department.";
    public static final String INVALID_SEARCH_MSG = "Invalid input. Please input Search category(ID/Name/Department) and an ID";
    public static final String INVALID_CMD_MSG = "Invalid Command. Try again";
    public static final String EMPLOYEE_NOTFOUND_MSG = "Employee with this ID not found";
    public static final String INVALID_NUM_VALUES_MSG = "Invalid input. Please provide valid numeric value";

}
