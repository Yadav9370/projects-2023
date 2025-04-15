import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LoginDao {
    private static final String url = "jdbc:mysql://localhost:3306/secure";
    private static final String username = "root";
    private static final String password = "asdfghjkl";

    public boolean validateUser(LoginPojo user) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(url, username, password);

            // SQL query to validate user credentials
            String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());

            // Execute the query
            rs = stmt.executeQuery();
            if (rs.next()) {
                // User exists and credentials are correct
                isValid = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connections
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValid;
    }

    public boolean insertUser(LoginPojo user) {
        boolean inserted = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(url, username, password);

            // SQL query to insert user data
            String sql = "INSERT INTO user (name, password, created_at) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            
            // Set current timestamp as creation time
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(3, timestamp);

            // Execute the query
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                // Successfully inserted
                inserted = true;
                System.out.println("User inserted successfully at: " + timestamp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connections
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inserted;
    }
}
