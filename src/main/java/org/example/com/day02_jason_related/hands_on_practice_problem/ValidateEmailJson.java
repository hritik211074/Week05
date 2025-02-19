package org.example.com.day02_jason_related.hands_on_practice_problem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

import java.util.Set;

public class ValidateEmailJson {
    public static void main(String[] args) throws Exception {
        // Define JSON Schema for email validation
        String schemaStr = """
      
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
       }""";

        // JSON data to validate
        String jsonData = "{ \"email\": \"test@example.com\" }";

        // Convert JSON string to Jackson JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(schemaStr);
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        // Create JSON Schema validator with format validation enabled
        SchemaValidatorsConfig config = new SchemaValidatorsConfig();
        config.addFormat("email", new EmailFormatValidator()); // Manual email format validator

        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = schemaFactory.getSchema(schemaNode, config);

        // Validate JSON data
        Set<ValidationMessage> errors = schema.validate(jsonNode);

        // Print validation result
        if (errors.isEmpty()) {
            System.out.println("Valid email!");
        } else {
            System.out.println("Invalid email: " + errors);
        }
    }
}