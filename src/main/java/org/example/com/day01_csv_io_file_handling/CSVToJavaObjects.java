package org.example.com.day01_csv_io_file_handling;
import java.io.*;
import java.util.*;

// Student class
class Student {
    private int id;
    private String name;
    private int age;
    private double marks;

    // Constructor
    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Override toString for easy printing
    @Override
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Age=" + age +
                ", Marks=" + marks +
                '}';
    }
}

public class CSVToJavaObjects {
    public static void main(String[] args) {
        String filePath = "D:\\Training_Assessment_Week_05\\src\\main\\java\\org\\example\\com\\day01_csv_io_file_handling\\students.csv";
        // Path to CSV file
        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                double marks = Double.parseDouble(data[3].trim());

                // Create Student object and add to list
                studentList.add(new Student(id, name, age, marks));
            }

            // Print all students
            System.out.println("List of Students:");
            for (Student student : studentList) {
                System.out.println(student);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }
    }
}
