import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EncryptionDao {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/secure";
    private static final String USER = "root";
    private static final String PASSWORD = "asdfghjkl";

    public void insertEncryptionParams(EncryptionPojo encryptionPojo) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmtKey = null;
        PreparedStatement pstmtSalt = null;
        PreparedStatement pstmtAuthTag = null;
        PreparedStatement pstmtFilePath = null;
        PreparedStatement pstmtNonce = null;

        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            

            // SQL insert statement for file_path
            String sqlFilePath = "INSERT INTO file_path_table (file_path) VALUES (?)";
            pstmtFilePath = conn.prepareStatement(sqlFilePath);
            pstmtFilePath.setString(1, encryptionPojo.getEncryptedFilePath());
            pstmtFilePath.executeUpdate();

           

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmtKey != null) pstmtKey.close();
                if (pstmtSalt != null) pstmtSalt.close();
                if (pstmtAuthTag != null) pstmtAuthTag.close();
                if (pstmtFilePath != null) pstmtFilePath.close();
                if (pstmtNonce != null) pstmtNonce.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
