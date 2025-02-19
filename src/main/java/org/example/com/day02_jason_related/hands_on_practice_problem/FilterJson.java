package org.example.com.day02_jason_related.hands_on_practice_problem;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.*;

public class FilterJson {

    public static void main(String[] args) throws Exception {
        // Read JSON file as a string
        String content = Files.readString(Paths.get("D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day02_jason\\hands_on_practice_problem\\users.json"));

        // Convert to JSON array
        JSONArray users = new JSONArray(content);

        // Filter and print users older than 25
        users.forEach(obj -> {
            JSONObject user = (JSONObject) obj;
            if (user.getInt("age") > 25) {
                System.out.println(user);
            }
        });
    }
}
