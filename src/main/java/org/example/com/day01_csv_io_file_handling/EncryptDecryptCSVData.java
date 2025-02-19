package org.example.com.day01_csv_io_file_handling;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

import static jdk.javadoc.internal.doclets.formats.html.markup.TagName.HR;

public class EncryptDecryptCSVData {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456"; // 16-byte key for AES-128

    public static void main(String[] args) {
        String csvFilePath = "employees.csv"; // Path to the CSV file
        String encryptedCsvFilePath = "encrypted_employees.csv"; // Path to the encrypted CSV file

        // Sample data
        String[] employees = {
                "101,John Doe,john.doe@example.com,Engineering,75000",
                "102,Jane Smith,jane.smith@example.com,Marketing,65000",
                "103,David Johnson,david.johnson@example.com,Sales,55000"


        };

        // Encrypt and write to CSV
        encryptAndWriteCSV(employees, encryptedCsvFilePath);

        // Read the encrypted CSV and decrypt the data
        readAndDecryptCSV(encryptedCsvFilePath);
    }

    // Method to encrypt data using AES
    private static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedData); // Encoding in Base64 to store as string
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    // Method to decrypt data using AES
    private static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedData = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }

    // Method to encrypt and write data to a CSV file
    private static void encryptAndWriteCSV(String[] data, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("ID,Name,Email,Department,Salary");
            bw.newLine();

            for (String record : data) {
                String[] fields = record.split(",");
                String encryptedEmail = encrypt(fields[2]);  // Encrypt email
                String encryptedSalary = encrypt(fields[4]); // Encrypt salary
                bw.write(fields[0] + "," + fields[1] + "," + encryptedEmail + "," + fields[3] + "," + encryptedSalary);
                bw.newLine();
            }
            System.out.println("Encrypted CSV written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing the encrypted CSV: " + e.getMessage());
        }
    }

    // Method to read and decrypt data from a CSV file
    private static void readAndDecryptCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header row

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String decryptedEmail = decrypt(fields[2]);  // Decrypt email
                String decryptedSalary = decrypt(fields[4]); // Decrypt salary
                System.out.println("ID: " + fields[0] + ", Name: " + fields[1] + ", Email: " + decryptedEmail
                        + ", Department: " + fields[3] + ", Salary: " + decryptedSalary);
            }
        } catch (IOException e) {
            System.out.println("Error reading the encrypted CSV: " + e.getMessage());
        }
    }
}
