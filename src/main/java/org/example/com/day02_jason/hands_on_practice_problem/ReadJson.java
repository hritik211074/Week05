package org.example.com.day02_jason.hands_on_practice_problem;

import java.nio.file.*;   // For reading the file
import org.json.JSONObject; // JSON library for parsing

public class ReadJson {
    public static void main(String[] args) throws Exception {
        // Read the entire JSON file as a string
        String content = Files.readString(Paths.get("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason\\hands_on_practice_problem\\data.json"));

        // Convert the string into a JSONObject and print all key-value pairs
        new JSONObject(content).toMap().forEach((k, v) -> System.out.println(k + ": " + v));
    }
}