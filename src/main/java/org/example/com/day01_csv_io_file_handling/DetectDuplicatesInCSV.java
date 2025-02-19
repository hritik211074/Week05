package org.example.com.day01_csv_io_file_handling;

import java.io.*;
import java.util.*;

public class DetectDuplicatesInCSV {
    public static void main(String[] args) {
        String filePath = "students.csv"; // Path to your CSV file
        Set<String> uniqueIds = new HashSet<>(); // Set to store unique IDs
        List<String> duplicateRecords = new ArrayList<>(); // List to store duplicate records

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header row (if any)

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0].trim(); // Assuming ID is in the first column

                // Check if the ID already exists in the set (i.e., duplicate)
                if (uniqueIds.contains(id)) {
                    duplicateRecords.add(line); // Add the duplicate record to the list
                } else {
                    uniqueIds.add(id); // Add the ID to the set
                }
            }

            // Print duplicate records
            if (duplicateRecords.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate Records:");
                for (String record : duplicateRecords) {
                    System.out.println(record);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
