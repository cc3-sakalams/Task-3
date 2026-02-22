package company;

import java.sql.*;

public class SQLiteExample {
    
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            
            conn = DriverManager.getConnection("jdbc:sqlite:example.db");
            System.out.println("✅ Connected to SQLite successfully!");

            Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                         "id INTEGER PRIMARY KEY, " +
                         "name TEXT NOT NULL, " +
                         "age INTEGER)");
            
            stmt.executeUpdate("DELETE FROM users");

            String[][] sampleUsers = {
                {"John Cena", "20"},
                {"Aron Fulay", "20"},
                {"Jhustine Genora", "19"},
                {"Julie Brown", "22"},
                {"Charlie Davis", "20"},
                {"Reign Evans", "21"},
                {"Rhainier Foster", "18"},
                {"Kenjay Green", "23"},
                {"Christopher Harris", "20"},
                {"Kyrie Irving", "22"}
            };
            System.out.println("\nInserting 10 students...");
            for (String[] user : sampleUsers) {
                String insertSQL = "INSERT INTO users (name, age) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, user[0]);
                    pstmt.setInt(2, Integer.parseInt(user[1]));
                    pstmt.executeUpdate();
                }
            }
            System.out.println("✅ 10 students inserted successfully!");
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
