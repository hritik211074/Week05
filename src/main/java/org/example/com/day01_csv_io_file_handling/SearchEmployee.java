package org.example.com.day01_csv_io_file_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchEmployee {
    public static void main(String[] args) {
        String filePath = "D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\employees.csv";
        String line;
        boolean found = false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine().trim();
        scanner.close();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header row
            String header = br.readLine();

            // Read each record and search for the employee
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[1].trim(); // Assuming Name is in the 2nd column

                if (name.equalsIgnoreCase(searchName)) {
                    System.out.println("Employee Found:");
                    System.out.println("Department: " + data[2]); // Department column
                    System.out.println("Salary: $" + data[3]); // Salary column
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
