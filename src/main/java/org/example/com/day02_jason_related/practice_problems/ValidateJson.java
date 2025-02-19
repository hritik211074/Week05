package org.example.com.day02_jason_related.practice_problems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

// Create ValidateJson class to validate JSON
class ValidateJson {
    public static void main(String[] args) {
        // Create an instance of Object mapper
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON file and parse it into JsonNode
            JsonNode jsonNode = objectMapper.readTree(new File("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason\\practice_problems\\Person.Json"));

            // Validate if required fields exist
            if (jsonNode.has("name") && jsonNode.has("email") && jsonNode.has("age") && jsonNode.has("city")) {
                System.out.println("JSON is valid.");
            } else {
                System.out.println("JSON is invalid: Missing required fields.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}