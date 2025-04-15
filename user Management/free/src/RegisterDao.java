import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
    private static final String URL = "jdbc:mysql://localhost:3306/free";
    private static final String USER = "root";
    private static final String PASSWORD = "asdfghjkl";

    public boolean registerUser(RegisterPojo user) {
        boolean status = false;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO users (username, mobile, password) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getMobile());
            pstmt.setString(3, user.getPassword());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return status;
    }
}