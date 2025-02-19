package org.example.com.day01_csv_io_file_handling;

import java.io.*;

public class ReadLargeCSV {
    public static void main(String[] args) {
        int batchSize = 100; // Number of lines to process at a time
        int recordCount = 0;

        String filePath="D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\hugefile.csv";
        ;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int batchCounter = 0;

            // Skip the header row (if any)
            String header = br.readLine();
            System.out.println("Header: " + header);

            while ((line = br.readLine()) != null) {
                batchCounter++;

                // Process each line (Here we're just counting the lines for simplicity)
                recordCount++;

                // If batch size is reached, process the batch and reset counter
                if (batchCounter == batchSize) {
                    System.out.println("Processed " + batchSize + " records.");
                    batchCounter = 0; // Reset the batch counter
                }
            }

            // Process any remaining records less than batch size
            if (batchCounter > 0) {
                System.out.println("Processed " + batchCounter + " remaining records.");
            }

            System.out.println("Total records processed: " + recordCount);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
