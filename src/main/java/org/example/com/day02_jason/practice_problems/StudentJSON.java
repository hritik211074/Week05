package org.example.com.day02_jason.hands_on_practice_problem;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

// Create ReadJsonFile class to read JSON file and extract specific fields
class StudentJSON {
    public static void main(String[] args) {
        try {
            // Read JSON file content as a string
            String content = new String(Files.readAllBytes(Paths.get("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason\\hands_on_practice_problem\\Person.Json")));

            // Convert string to JSON array
            JSONArray jsonArray = new JSONArray(content);

            // Loop through JSON array and extract specific fields
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject person = jsonArray.getJSONObject(i);

                String name = person.getString("name");
                String email = person.getString("email");
                int age = person.getInt("age");
                String city = person.getString("city");

                // Print extracted fields
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Age: " + age);
                System.out.println("City: " + city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
