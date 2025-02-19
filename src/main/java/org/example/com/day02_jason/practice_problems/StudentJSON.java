package org.example.com.day02_jason.practice_problems;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Arrays;

// Create a JSON object with fields name, age and subject
class StudentJSON {
    public static void main(String[] args) {
        // Create JSON object and put element
        JSONObject studentJson = new JSONObject();
        studentJson.put("name", "Hritik");
        studentJson.put("age", 20);
        studentJson.put("subjects", new JSONArray(Arrays.asList("Math", "Science", "History")));

        // Printing JSON object
        System.out.println(studentJson.toString());
    }
}