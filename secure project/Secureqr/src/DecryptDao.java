import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DecryptDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/secure";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "asdfghjkl";

    private Connection connection;

    public DecryptDao() throws ClassNotFoundException, SQLException {
        // Load the database driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Establish the database connection
        this.connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public void insertPassword(String password) throws SQLException {
        String query = "INSERT INTO encryption_parameters (password) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, password);
            ps.executeUpdate();
        }
    }

    public PasswordPojo getPasswordPojo() throws SQLException {
        String query = "SELECT password FROM encryption_parameters LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                PasswordPojo passwordPojo = new PasswordPojo();
                passwordPojo.setPassword(rs.getString("password"));
                return passwordPojo;
            } else {
                return null;
            }
        }
    }
}
