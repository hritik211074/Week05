package org.example.com.day02_jason_related.hands_on_practice_problem;

import org.json.JSONObject;
import org.json.XML;

public class JsonToXmlConverter {
    public static void main(String[] args) {
        // JSON Data
        String jsonData = """
       {
           "name": "Suresh",
           "age": 30,
           "city": "Champaran"
       }
       """;

        // Convert JSON to XML
        JSONObject jsonObject = new JSONObject(jsonData);
        String xmlData = XML.toString(jsonObject, "root"); // "root" is the XML root element

        // Print XML Output
        System.out.println(xmlData);
    }
}