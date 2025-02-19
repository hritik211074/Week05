package org.example.com.day01_csv_io_file_handling;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateCSV {
    public static void main(String[] args) {
        String inputFile = "employees.csv"; // Original file
        String outputFile = "updated_employees.csv"; // Updated file
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            // Read and store all records
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                // Update salary for IT department employees
                if (data[2].trim().equalsIgnoreCase("IT")) {
                    double salary = Double.parseDouble(data[3].trim());
                    salary *= 1.10; // Increase by 10%
                    data[3] = String.format("%.2f", salary); // Format to 2 decimal places
                }
                records.add(data);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Write updated data to a new CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String[] record : records) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
            System.out.println("Updated CSV file created: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error writing the file: " + e.getMessage());
        }
    }
}
