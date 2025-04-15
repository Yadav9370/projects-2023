import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {

    public List<SearchPojo> searchByTerm(String searchTerm) throws SQLException {
        List<SearchPojo> users = new ArrayList<>();

        String jdbcURL = "jdbc:mysql://localhost:3306/free";
        String dbUser = "root";
        String dbPassword = "asdfghjkl";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                String sql = "SELECT username, invitation, invitationLink FROM users " +
                             "WHERE invitation = ? OR invitationLink = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, searchTerm);
                    statement.setString(2, searchTerm);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            SearchPojo user = new SearchPojo();
                            String invitation = resultSet.getString("invitation");
                            String invitationLink = resultSet.getString("invitationLink");

                            user.setUsername(resultSet.getString("username"));

                            // Determine which data to display in invitation code and designation
                            if (searchTerm.equals(invitation)) {
                                user.setInvitation(invitation);
                                user.setDesignation("Joiner");
                            } else if (searchTerm.equals(invitationLink)) {
                                user.setInvitation(invitationLink);
                                user.setDesignation("Owner");
                            }

                            users.add(user);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        return users;
    }
}
