import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/free";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "asdfghjkl"; // Update with your database password

    // Load the MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check if username exists
    public boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if username exists
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if mobile number exists
    public boolean isMobileExists(String mobile) {
        String query = "SELECT COUNT(*) FROM users WHERE mobile = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, mobile);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if mobile number exists
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Insert new user
    public boolean registerUser(String username, String mobile, String password) {
        String query = "INSERT INTO users (username, mobile, password) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, mobile);
            ps.setString(3, password);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; // Return true if insertion is successful
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
