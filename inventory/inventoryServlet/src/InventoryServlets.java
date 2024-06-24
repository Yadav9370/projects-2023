

import inventoryServlet.Inventory;
import inventoryServlet.InventoryDAO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class InventoryServlets
 */
public class InventoryServlets extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InventoryServlets() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // No implementation needed for now
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String stockName = request.getParameter("stockName");
        int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
        int availableQuantity = Integer.parseInt(request.getParameter("availableQuantity"));
        String dateOfSupplyStr = request.getParameter("dateOfSupply");
        java.util.Date dateOfSupply = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateOfSupply = sdf.parse(dateOfSupplyStr);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            // Handle parsing error
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String recentSupplyTrends = request.getParameter("recentSupplyTrends");
        int minStockLevel = Integer.parseInt(request.getParameter("minStockLevel"));

        Inventory inventory = new Inventory();
        inventory.setStockName(stockName);
        inventory.setAvailableQuantity(availableQuantity);
        inventory.setStockQuantity(stockQuantity);
        inventory.setDateOfSupply(dateOfSupply);
        inventory.setRecentSupplyTrends(recentSupplyTrends);
        inventory.setMinStockLevel(minStockLevel);

        InventoryDAO inventoryDAO = new InventoryDAO();
        boolean isInserted = false;
        try {
            isInserted = inventoryDAO.insertInventory(inventory);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle database error
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (isInserted) {
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
    }
    }
}

