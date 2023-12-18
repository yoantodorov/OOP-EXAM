package com.sirmaacademy.app;

import com.sirmaacademy.app.datasource.Constants;
import com.sirmaacademy.app.datasource.FileReader;
import com.sirmaacademy.app.datasource.FileWriter;
import com.sirmaacademy.app.managers.Manager;
import com.sirmaacademy.app.managers.StaffManager;
import com.sirmaacademy.app.services.Service;
import com.sirmaacademy.app.services.StaffService;

import java.util.Scanner;

public class StaffManagementApp {
    public static void main(String[] args) {
        FileReader reader = new FileReader();
        FileWriter writer = new FileWriter();
        Service service = new StaffService(reader, writer);
        Manager manager = new StaffManager(service);

        System.out.println(Constants.WELCOME_MSG);
        displayCommands();
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean isRunning = true;
        while (isRunning) {
            command = scanner.nextLine();
            manager.execute(command);
            System.out.print("Command: ");
        }
    }

    private static void displayCommands() {
        System.out.println("Supported Commands: ");
        System.out.println("    Add Employee");
        System.out.println("    Edit {Employee ID}                  (Example: Edit 4)");
        System.out.println("    List");
        System.out.println("    Search ID/Name/Department           (Example: Search ID 4)");
        System.out.println("    Fire {Employee ID}                  (Example: Fire 4)");
        System.out.println("    Save & Exit");
        System.out.println("    Exit");
        System.out.print("Command: ");
    }
}
