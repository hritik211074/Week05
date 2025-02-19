package org.example.com.day01_csv_io_file_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    public static void main(String[] args) {

        String filePath = "D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\students.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read header (optional)
            String header = br.readLine();
            System.out.println("Student Details:\n-------------------------");

            // Read and print each record
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.printf("ID: %s, Name: %s, Age: %s, Marks: %s%n",
                        data[0], data[1], data[2], data[3]);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
