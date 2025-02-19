package org.example.com.day01_csv_io_file_handling;

import java.io.*;
import java.util.regex.*;

public class ValidateCSVData {
    public static void main(String[] args) {
        String filePath = "D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\employees.csv"; // CSV file path
        String line;

        // Regex patterns and Valid email pattern
        String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";

        // Exactly 10 digits for phone number
        String phoneRegex = "^\\d{10}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read header row
            String header = br.readLine();
            System.out.println("Checking for invalid records...\n");

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Assuming Email is in the 4th column
                String email = data[3].trim();
                // Assuming Phone Number is in the 5th column

                String phone = data[4].trim();

                boolean isEmailValid = emailPattern.matcher(email).matches();
                boolean isPhoneValid = phonePattern.matcher(phone).matches();

                if (!isEmailValid || !isPhoneValid) {
                    System.out.println("Invalid Record: " + line);
                    if (!isEmailValid) {
                        System.out.println("  ➤ Invalid Email: " + email);
                    }
                    if (!isPhoneValid) {
                        System.out.println("  ➤ Invalid Phone Number: " + phone);
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
