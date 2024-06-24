package inventoryServlet;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/sdacproject";
    private String jdbcUsername = "root";
    private String jdbcPassword = "asdfghjkl";
    private String jdbcDriver = "com.mysql.jdbc.Driver";

    private static final String SELECT_USER = "SELECT * FROM signsup WHERE username = ? AND password = ?";

    public LoginDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean validate(Login login) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
