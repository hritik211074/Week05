package org.example.com.day02_jason.hands_on_practice_problem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.File;

public class DatabaseToJsonReport {
    public static void main(String[] args) {
        // Database URL
        String url = "jdbc:mysql://localhost:3306/company_db";
        // Database Username
        String user = "root";
        // Database Password
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {

            // Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode jsonArray = objectMapper.createArrayNode();

            // Convert ResultSet to JSON
            while (rs.next()) {
                ObjectNode record = objectMapper.createObjectNode();
                record.put("id", rs.getInt("id"));
                record.put("name", rs.getString("name"));
                record.put("age", rs.getInt("age"));
                record.put("department", rs.getString("department"));
                jsonArray.add(record);
            }

            // Save JSON to file
            File jsonFile = new File("employee_report.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonArray);

            System.out.println("JSON report generated successfully: " + jsonFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}