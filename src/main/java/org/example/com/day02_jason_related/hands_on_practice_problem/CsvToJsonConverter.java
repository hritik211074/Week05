package org.example.com.day02_jason_related.hands_on_practice_problem;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvToJsonConverter {

    public static void main(String[] args) {
        try {
            // CSV file path
            File csvFile = new File("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason\\hands_on_practice_problem\\data.csv");

            // Print the absolute path for debugging
            System.out.println("CSV file path: " + csvFile.getAbsolutePath());

            // Check if file exists
            if (!csvFile.exists()) {
                System.err.println("Error: CSV file not found at " + csvFile.getAbsolutePath());
                return;
            }

            // Define CSV schema (auto-detect headers)
            CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

            // Read CSV and convert to JSON
            CsvMapper csvMapper = new CsvMapper();
            MappingIterator<JsonNode> jsonTree = csvMapper.readerFor(JsonNode.class)
                    .with(csvSchema)
                    .readValues(csvFile);

            List<JsonNode> jsonList = jsonTree.readAll();


            // Convert JSON tree to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonTree);

            // Print JSON output for debugging
            System.out.println("JSON output: " + jsonOutput);

            // Optionally, write the JSON output to a file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("data.json"), jsonTree);

        } catch (IOException e) {
            System.err.println("Error occurred while converting CSV to JSON: " + e.getMessage());
        }
    }
}