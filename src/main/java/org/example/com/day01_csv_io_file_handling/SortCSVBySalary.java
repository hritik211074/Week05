package org.example.com.day01_csv_io_file_handling;

import java.io.*;
import java.util.*;

public class SortCSVBySalary {
    public static void main(String[] args) {
        String filePath = "D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\employees.csv"; // Path to CSV file
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // Read and store the header
            System.out.println("Top 5 Highest-Paid Employees:");
            System.out.println(header);

            String line;
            while ((line = br.readLine()) != null) {
                records.add(line.split(",")); // Store employee records
            }

            // Sort records by Salary in descending order
            records.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));

            // Print the top 5 highest-paid employees
            for (int i = 0; i < Math.min(5, records.size()); i++) {
                System.out.println(String.join(",", records.get(i)));
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing salary: " + e.getMessage());
        }
    }
}
