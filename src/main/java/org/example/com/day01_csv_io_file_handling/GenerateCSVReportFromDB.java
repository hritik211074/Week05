package org.example.com.day01_csv_io_file_handling;

import java.io.*;
import java.sql.*;

public class GenerateCSVReportFromDB {
    public static void main(String[] args) {

        // JDBC URL for MySQL
        String jdbcUrl = "jdbc:mysql://localhost:3306/your_database";


        // Database username
        String jdbcUser = "hritik_7352";

        // Database password
        String jdbcPassword = "hr_2003";
        // SQL Query
        String query = "SELECT employee_id, name, department, salary FROM employees";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             BufferedWriter bw = new BufferedWriter(new FileWriter("employee_report.csv"))) {

            // Write CSV headers
            bw.write("Employee ID,Name,Department,Salary");
            bw.newLine();

            // Write data from the result set into the CSV file
            while (rs.next()) {
                int id = rs.getInt("employee_id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double salary = rs.getDouble("salary");

                // Write a single record into the CSV file
                bw.write(id + "," + name + "," + department + "," + salary);
                bw.newLine();
            }

            System.out.println("CSV report generated successfully.");
        } catch (SQLException | IOException e) {
            System.out.println("Error generating CSV report: " + e.getMessage());
        }
    }
}
