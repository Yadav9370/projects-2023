package inventoryServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class UpdateDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/sdacproject";
    private String jdbcUsername = "root";
    private String jdbcPassword = "asdfghjkl";

    private static final String UPDATE_INVENTORY_SQL = "UPDATE inventorytable SET  stockName=?, stockQuantity = ?, availableQuantity = ?, dateOfSupply = ?, recentSupplyTrends = ?, minStockLevel = ? WHERE id = ?;";

    public UpdateDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean updateInventory(UpdateInventory inventoryUpdate) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVENTORY_SQL)) {
        	preparedStatement.setString(1, inventoryUpdate.getStockName());
            preparedStatement.setInt(2, inventoryUpdate.getStockQuantity());
            preparedStatement.setInt(3, inventoryUpdate.getAvailableQuantity());
            java.util.Date utilDate = inventoryUpdate.getDateOfSupply();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            
            preparedStatement.setDate(4, sqlDate);
            
            preparedStatement.setString(5, inventoryUpdate.getRecentSupplyTrends());
            preparedStatement.setInt(6, inventoryUpdate.getMinimumStockLevel());
            preparedStatement.setInt(7, inventoryUpdate.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }
}
