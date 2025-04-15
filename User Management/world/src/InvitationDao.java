import java.sql.*;

public class InvitationDao {
    // Database connection method (assumes you have a connection pool or DriverManager setup)
    private Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/free", "root", "asdfghjkl");
    }

    // Verify if username exists
    public boolean verifyUsername(String username) {
        boolean isValid = false;
        String query = "SELECT 1 FROM users WHERE username = ?";
        try (Connection connection = getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    // Get the existing referral code for the given username
    public String getReferralCode(String username) {
        String referralCode = null;
        String query = "SELECT invitationlink FROM users WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                referralCode = rs.getString("invitationlink");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return referralCode;
    }

    // Save referral code to the database (Update the invitation field for the given username)
    public boolean saveInvitation(String username, String invitation) {
        String query = "UPDATE users SET invitationlink = ? WHERE username = ?";
        try (Connection connection = getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, invitation);
            stmt.setString(2, username);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
