package org.example.com.day01_csv_io_file_handling;

import java.io.*;
import java.util.*;

public class MergeCSVFiles {

    public static void main(String[] args) {

        String file1Path = "students1.csv"; // Path to first CSV file (students1.csv)

        String file2Path = "students2.csv"; // Path to second CSV file (students2.csv)

        String outputFilePath = "merged_students.csv"; // Output file path

        Map<String, String[]> studentData = new HashMap<>(); // Map to store student data by ID

        // Reading the first CSV file (students1.csv)
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1Path))) {
            String line;
            br1.readLine(); // Skip header row

            while ((line = br1.readLine()) != null) {

                String[] data = line.split(",");
                String id = data[0].trim();
                String name = data[1].trim();
                String age = data[2].trim();
                studentData.put(id, new String[]{name, age});
            }
        } catch (IOException e) {
            System.out.println("Error reading students1.csv: " + e.getMessage());
        }

        // Reading the second CSV file (students2.csv) and merging the data
        try (BufferedReader br2 = new BufferedReader(new FileReader(file2Path))) {
            String line;
            br2.readLine(); // Skip header row
            while ((line = br2.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0].trim();
                String marks = data[1].trim();
                String grade = data[2].trim();

                // If the ID exists in the first file, merge the data
                if (studentData.containsKey(id)) {
                    String[] studentInfo = studentData.get(id);
                    String[] mergedData = new String[]{id, studentInfo[0], studentInfo[1], marks, grade};

                    // Write the merged data to the output file
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath, true))) {
                        bw.write(String.join(",", mergedData));
                        bw.newLine();
                    } catch (IOException e) {
                        System.out.println("Error writing to merged_students.csv: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading students2.csv: " + e.getMessage());
        }

        System.out.println("Merging completed. Data saved in merged_students.csv.");
    }
}
