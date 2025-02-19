package org.example.com.day01_csv_io_file_handling;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountCSVRows {

    public static void main(String[] args) {
        String filePath = "D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\students.csv";
        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read and ignore the header row
            String line = br.readLine();

            // Count remaining records
            while ((line = br.readLine()) != null) {
                rowCount++;
            }

            System.out.println("Total records (excluding header): " + rowCount);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
