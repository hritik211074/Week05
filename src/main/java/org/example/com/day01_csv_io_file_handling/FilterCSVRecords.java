package org.example.com.day01_csv_io_file_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterCSVRecords {
    public static void main(String[] args) {
        String filePath = "D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\students.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header row
            String header = br.readLine();
            System.out.println("Students with marks greater than 80:");
            System.out.println(header); // Print header for reference

            // Read and filter records
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int marks = Integer.parseInt(data[3]); // Assuming marks are in the 4th column

                if (marks > 80) {
                    System.out.println(line); // Print only qualifying records
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing marks: " + e.getMessage());
        }
    }
}
