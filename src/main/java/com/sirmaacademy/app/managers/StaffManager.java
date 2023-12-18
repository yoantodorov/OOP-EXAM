package com.sirmaacademy.app.managers;

import com.sirmaacademy.app.datasource.Constants;
import com.sirmaacademy.app.entities.Department;
import com.sirmaacademy.app.entities.Employee;
import com.sirmaacademy.app.services.Service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StaffManager implements Manager {
    private final Service<Employee> service;

    public StaffManager(Service<Employee> service) {
        this.service = service;
    }


    @Override
    public void execute(String command) {
        String commandLwr = command.toLowerCase();
        switch (commandLwr) {
            case "add employee" -> {handleAddCommand();return;}
            case "list" -> {handleListCommand();return;}
            case "exit" -> {handleExitCommand();return;}
            case "save" -> {handleSaveCommand();return;}
            case "save & exit", "save and exit" -> {
                handleSaveCommand();
                handleExitCommand();
                return;
            }
        }
        String[] cmdParts = commandLwr.split("\\s+");
        switch (cmdParts[0]) {
            case "fire" -> {
                if (cmdParts.length == 2) {
                    handleFireCommand(cmdParts[1]);
                } else {
                    System.out.println(Constants.INVALID_NUM_VALUES_MSG);
                }
            }
            case "edit" -> {
                if (cmdParts.length == 2) {
                    handleEditCommand(cmdParts[1]);
                } else {
                    System.out.println(Constants.INVALID_NUM_VALUES_MSG);
                }

            }
            case "search" -> {
                if (cmdParts.length == 3) {
                    handleSearchCommand(cmdParts[1], cmdParts[2]);
                } else {
                    System.out.println(Constants.INVALID_SEARCH_MSG);
                }
            }
            default -> System.out.println(Constants.INVALID_CMD_MSG);
        }
    }

    private void handleEditCommand(String idStr) {
        int id = validateId(idStr);
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            service.getData().remove(employee);
            System.out.println("Editing employee: " + employee);
            handleAddCommand();
        } else {
            System.out.println(Constants.EMPLOYEE_NOTFOUND_MSG);
        }

    }

    private void handleSearchCommand(String cmdType, String parameter) {
        switch (cmdType) {
            case "name" -> handleSearchByName(parameter);
            case "id" -> handleSearchById(parameter);
            case "department" -> handleSearchByDepartment(parameter);
            default -> System.out.println(Constants.INVALID_SEARCH_MSG);
        }

    }

    private void handleSearchByDepartment(String parameter) {
        Department department = validateDepartment(parameter);
        if (department == null) {
            System.out.println(Constants.INVALID_DEPARTMENT_MSG);
        } else {
            listEmployeesInDepartment(department);
        }


    }

    private void listEmployeesInDepartment(Department department) {
        for (Employee employee : service.getData()) {
            if (employee.getDepartment() == department) {
                System.out.println(employee);
            }
        }
    }

    private void handleSearchById(String parameter) {
        int id = validateId(parameter);
        if (id != -1) {
            for (Employee emp : service.getData()) {
                if (emp.getId() == id) {
                    System.out.println(emp);
                }
            }
        }
    }

    private int validateId(String idStr) {
        try {
            return Integer.parseInt(idStr);

        } catch (NumberFormatException e) {
            System.out.println(Constants.INVALID_NUM_VALUES_MSG);
        }
        return -1;
    }

    private void handleSearchByName(String parameter) {
        String regex = "\\b" + Pattern.quote(parameter) + "\\b";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        for (Employee emp : service.getData()) {
            if (pattern.matcher(emp.getName()).find()) {
                System.out.println(emp);
            }
        }
    }

    private void handleFireCommand(String idStr) {
            int id = validateId(idStr);
            Employee employee = findEmployeeById(id);
            if (employee != null) {
                service.removeData(employee);
                System.out.println("Employee fired: " + employee);
            } else {
                System.out.println(Constants.EMPLOYEE_NOTFOUND_MSG);
            }
    }

    private Employee findEmployeeById(int id) {
        for (Employee employee : service.getData()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    private void handleExitCommand() {
        System.exit(0);
    }

    private void handleSaveCommand() {
        service.write(Constants.EMPLOYEES_FILE_PATH);
    }


    private void handleListCommand() {
        List<Employee> employeeList = service.getData();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    private void handleAddCommand() {
        while (true) {
            System.out.println(Constants.EMPLOYEE_DETAILS_MSG);
            Scanner scanner = new Scanner(System.in);
            String employeeLine = scanner.nextLine();
            String[] employeeToBe = employeeLine.split("\\s*,\\s*");

            if (employeeLine.equalsIgnoreCase("cancel")){
                return;
            }

            if (employeeToBe.length != 5) {
                System.out.println(Constants.INVALID_FIELDS_MSG);
            } else {
                try {
                    int id = Integer.parseInt(employeeToBe[0].trim());
                    String name = employeeToBe[1].trim();
                    String departmentInput = employeeToBe[2].trim();
                    String position = employeeToBe[3].trim();
                    double salary = Double.parseDouble(employeeToBe[4].trim());

                    Department department = validateDepartment(departmentInput);
                    if (department == null) {
                        System.out.println(Constants.INVALID_DEPARTMENT_MSG);
                    } else {
                        Employee employee = new Employee(id, name, department, position, salary);

                        service.appendData(employee);
                        System.out.println("New employee added: " + employee);
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(Constants.INVALID_NUM_VALUES_MSG);
                }
            }
        }

    }

    private Department validateDepartment(String departmentInput) {
        try {
            return Department.valueOf(departmentInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
