import java.sql.*;

public class InsertDao {
    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/free", "root", "asdfghjkl");
    }
    
    public void insertAd(String username) throws SQLException {
        String sql = "UPDATE users SET status = 'yes' WHERE username = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}