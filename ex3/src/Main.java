import java.sql.*;
public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // Register JDBC driver
            Class.forName(JDBCExample.JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(JDBCExample.DB_URL, JDBCExample.USER, JDBCExample.PASS);

            // Create a table
            stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Employees "
                    + "(id SERIAL PRIMARY KEY, "
                    + "first VARCHAR(255), "
                    + "last VARCHAR(255), "
                    + "age INT)";
            stmt.executeUpdate(createTableSQL);

            String insertSQL = "INSERT INTO Employees (first, last, age) VALUES ('John', 'Doe', 30)";
            stmt.executeUpdate(insertSQL);

            ResultSet rs = stmt.executeQuery("SELECT id, first, last, age FROM Employees");
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }

            String updateSQL = "UPDATE Employees SET age = 31 WHERE first = 'John'";
            stmt.executeUpdate(updateSQL);

            rs = stmt.executeQuery("SELECT id, first, last, age FROM Employees");
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }

            String deleteSQL = "DELETE FROM Employees WHERE first = 'John'";
            stmt.executeUpdate(deleteSQL);

            rs = stmt.executeQuery("SELECT id, first, last, age FROM Employees");
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}