package org.example.com.day02_jason_related.hands_on_practice_problem;

import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateEmailJson {
    public static void main(String[] args) {
        try {
            // JSON Schema
            String schemaString = """
                {
                  "$schema": "https://json-schema.org/draft/2020-12/schema",
                  "type": "object",
                  "properties": {
                    "email": {
                      "type": "string",
                      "format": "email"
                    }
                  },
                  "required": ["email"]
                }
                """;

            // JSON Data (to be validated)
            String jsonString = """
                {
                  "email": "hritik@example.com"
                }
                """;

            ObjectMapper mapper = new ObjectMapper();
            JsonNode schemaNode = mapper.readTree(schemaString);
            JsonNode jsonNode = mapper.readTree(jsonString);

            // Create JSON Schema Validator
            JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();
            ProcessingReport report = validator.validate(schemaNode, jsonNode);

            // Print validation result
            if (report.isSuccess()) {
                System.out.println("Valid JSON: " + jsonString);
            } else {
                System.out.println("Invalid JSON: " + report);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}