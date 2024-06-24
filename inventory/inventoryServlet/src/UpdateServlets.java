

import inventoryServlet.UpdateInventory;
import inventoryServlet.UpdateDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateServlets extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateServlets() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No implementation needed for now
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String stockName = request.getParameter("stockName");
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        int availableQuantity = Integer.parseInt(request.getParameter("availableQuantity"));
        String dateOfSupplyStr = request.getParameter("dateOfSupply");
        Date dateOfSupply = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateOfSupply = sdf.parse(dateOfSupplyStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing error
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String recentSupplyTrends = request.getParameter("recentSupplyTrends");
        int minimumStockLevel = Integer.parseInt(request.getParameter("minimumStockLevel"));
        int id = Integer.parseInt(request.getParameter("id"));

        // Create UpdateInventory object and set properties
        UpdateInventory inventoryUpdate = new UpdateInventory();
        inventoryUpdate.setStockName(stockName);
        inventoryUpdate.setStockQuantity(stockQuantity);
        inventoryUpdate.setAvailableQuantity(availableQuantity);
        inventoryUpdate.setDateOfSupply(dateOfSupply);
        inventoryUpdate.setRecentSupplyTrends(recentSupplyTrends);
        inventoryUpdate.setMinimumStockLevel(minimumStockLevel);
        inventoryUpdate.setId(id);

        // Update inventory
        UpdateDAO inventoryUpdateDAO = new UpdateDAO();
        boolean isUpdated = inventoryUpdateDAO.updateInventory(inventoryUpdate);

        // Forward to the appropriate page based on success/failure
        RequestDispatcher dispatcher;
        if (isUpdated) {
            dispatcher = request.getRequestDispatcher("updatesuccess.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to update inventory data");
            dispatcher = request.getRequestDispatcher("error.jsp");
        }
        dispatcher.forward(request, response);
    }
}
