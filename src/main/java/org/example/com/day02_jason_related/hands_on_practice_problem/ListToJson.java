package org.example.com.day02_jason_related.hands_on_practice_problem;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Convert object to JSONObject
    JSONObject toJson() {
        return new JSONObject().put("name", name).put("age", age);
    }
}

public class ListToJson {
    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> people = List.of(new Person("Alice", 30), new Person("Bob", 25));

        // Convert list to JSONArray
        JSONArray jsonArray = new JSONArray(people.stream().map(Person::toJson).toArray());

        // Print the JSON array
        System.out.println(jsonArray.toString(2)); // Pretty print with indentation
    }
}
