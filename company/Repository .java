package company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Connection connection;
    private final String dbUrl = "jdbc:sqlite:students.db";

    public Repository() {
        try {
            connection = DriverManager.getConnection(dbUrl);
            System.out.println("Connected to SQLite database.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
    }

    public void createTableIfNotExist() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                "studentId TEXT PRIMARY KEY," +
                "firstName TEXT NOT NULL," +
                "lastName TEXT NOT NULL," +
                "age INTEGER NOT NULL," +
                "gender TEXT NOT NULL," +
                "course TEXT NOT NULL," +
                "yearLevel TEXT NOT NULL," +
                "contactNumber TEXT NOT NULL" +
                ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table created or already exists.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void insertStudent(Student student) {
        String insertSQL = "INSERT INTO students (studentId, firstName, lastName, age, gender, course, yearLevel, contactNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getFirstName());
            pstmt.setString(3, student.getLastName());
            pstmt.setInt(4, student.getAge());
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getCourse());
            pstmt.setString(7, student.getYearLevel());
            pstmt.setString(8, student.getContactNumber());
            pstmt.executeUpdate();
            System.out.println("Student inserted: " + student.getStudentId());
        } catch (SQLException e) {
            System.err.println("Error inserting student " + student.getStudentId() + ": " + e.getMessage());
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String selectSQL = "SELECT * FROM students;";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                Student student = new Student.Builder()
                        .withStudentId(rs.getString("studentId"))
                        .withFirstName(rs.getString("firstName"))
                        .withLastName(rs.getString("lastName"))
                        .withAge(rs.getInt("age"))
                        .withGender(rs.getString("gender"))
                        .withCourse(rs.getString("course"))
                        .withYearLevel(rs.getString("yearLevel"))
                        .withContactNumber(rs.getString("contactNumber"))
                        .build();

                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    public void listStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Master List of Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void closeResources() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}