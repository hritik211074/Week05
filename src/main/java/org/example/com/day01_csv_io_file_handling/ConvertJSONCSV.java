package org.example.com.day01_csv_io_file_handling;

import org.json.*;
import java.io.*;
import java.util.*;

public class ConvertJSONCSV {
    public static void main(String[] args) {

        // Path to your JSON file
        String jsonFilePath = "students.json";

        // Path to save the CSV file
        String csvFilePath = "students.csv";
        // Path to save the JSON file after conversion
        String outputJsonFilePath = "students_output.json";

        // Convert JSON to CSV
        convertJsonToCsv(jsonFilePath, csvFilePath);

        // Convert CSV back to JSON
        convertCsvToJson(csvFilePath, outputJsonFilePath);
    }

    // Method to convert JSON to CSV
    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(jsonFilePath));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // Parse JSON
            JSONArray jsonArray = new JSONArray(sb.toString());

            // Write to CSV file
            BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath));
            boolean isFirst = true;

            // Loop through the JSON array and write records to CSV
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject student = jsonArray.getJSONObject(i);
                if (isFirst) {
                    // Write header (keys)
                    String header = String.join(",", student.keySet());
                    bw.write(header);
                    bw.newLine();
                    isFirst = false;
                }

                // Write data (values)
                List<String> values = new ArrayList<>();
                for (String key : student.keySet()) {
                    values.add(student.getString(key));
                }
                String record = String.join(",", values);
                bw.write(record);
                bw.newLine();
            }

            bw.close();
            System.out.println("JSON to CSV conversion completed.");

        } catch (IOException | JSONException e) {
            System.out.println("Error converting JSON to CSV: " + e.getMessage());
        }
    }

    // Method to convert CSV to JSON
    public static void convertCsvToJson(String csvFilePath, String jsonFilePath) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            String line;
            List<JSONObject> jsonList = new ArrayList<>();
            String[] headers = null;

            // Read CSV file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (headers == null) {
                    headers = values; // Store headers (first row)
                } else {
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < values.length; i++) {
                        jsonObject.put(headers[i], values[i]);
                    }
                    jsonList.add(jsonObject);
                }
            }
            br.close();

            // Write JSON array to file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(jsonFilePath))) {

                // Pretty print JSON
                bw.write(new JSONArray(jsonList).toString(4));
            }
            System.out.println("CSV to JSON conversion completed.");

        } catch (IOException | JSONException e) {
            System.out.println("Error converting CSV to JSON: " + e.getMessage());
        }
    }
}
