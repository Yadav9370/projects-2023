import java.sql.*;
public class Register2Dao {
    private static Connection conn;
    
    public Register2Dao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/free", "root", "asdfghjkl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Check if username or mobile already exists
    public static boolean isUserExists(String username, String mobileNumber) {
        String sql = "SELECT 1 FROM users WHERE username = ? OR mobile = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, mobileNumber);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if username or mobile exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Validate invitation code by checking in the database
    public static boolean isValidInvitation(String code) {
        String sql = "SELECT 1 FROM users WHERE invitationlink = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, code);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if the invitation code exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Register user if invitation code is valid
    public static boolean registerUser(Register2Pojo user) {
        try {
            // First, check if the invitation code is valid
            if (!isValidInvitation(user.getInvitationCode())) {
                return false; // Invalid invitation code
            }
            
            // Insert user details
            String sql = "INSERT INTO users (username, mobile, password, invitation) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getMobileNumber());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getInvitationCode());
                int result = pstmt.executeUpdate();
                return result > 0; // Returns true if the user is inserted successfully
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}