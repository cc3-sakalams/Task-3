package company;

import java.sql.*;

public class SQLiteExample {
    
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Load SQLite JDBC driver (optional in newer drivers, but safe)
            Class.forName("org.sqlite.JDBC");
            
            // Connect (creates example.db in your project root if it doesn't exist)
            conn = DriverManager.getConnection("jdbc:sqlite:example.db");
            System.out.println("✅ Connected to SQLite successfully!");

            Statement stmt = conn.createStatement();

            // Create a sample table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                         "id INTEGER PRIMARY KEY, " +
                         "name TEXT NOT NULL, " +
                         "age INTEGER)");

            // Insert some data
            stmt.executeUpdate("INSERT INTO users (name, age) VALUES ('Alice', 25)");
            stmt.executeUpdate("INSERT INTO users (name, age) VALUES ('Bob', 30)");

            // Query and display
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            System.out.println("\n📋 Users in database:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                 ", Name: " + rs.getString("name") + 
                                 ", Age: " + rs.getInt("age"));
            }

            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}