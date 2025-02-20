package org.example.com.day02_jason_related.hands_on_practice_problem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class MergeJsonFiles {
    public static void main(String[] args) {
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON files
            JsonNode json1 = objectMapper.readTree(new File("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason_related\\hands_on_practice_problem\\file1.json"));
            JsonNode json2 = objectMapper.readTree(new File("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason_related\\hands_on_practice_problem\\file2.json"));
            // Merge JSON objects
            ObjectNode mergedJson = objectMapper.createObjectNode();
            mergedJson.setAll((ObjectNode) json1);
            mergedJson.setAll((ObjectNode) json2);

            // Print merged JSON
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson));

            // Optionally, write the merged JSON to a new file
            objectMapper.writeValue(new File("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason_related\\hands_on_practice_problem\\merged.json"), mergedJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}