

import inventoryServlet.DeleteInventory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        // Retrieve parameters from the request
		        int id = Integer.parseInt(request.getParameter("id"));
		        String stockName = request.getParameter("stockName");

		        // Create an instance of InventoryDAO
		        DeleteInventory inventoryDAO = new DeleteInventory();

		        // Check if name and id are proper
		        if (stockName == null || stockName.isEmpty() || id <= 0) {
		            // If name or id is not proper, show a pop-up and redirect back to the previous page
		            response.setContentType("text/html");
		            PrintWriter out = response.getWriter();
		            out.println("<script type=\"text/javascript\">");
		            out.println("alert('Please provide valid ID and stock name');");
		            out.println("history.back();");
		            out.println("</script>");
		        } else {
		            // Delete the inventory item
		            try {
						if (inventoryDAO.deleteInventory(id, stockName)) {
						    // If deletion successful, redirect to success.jsp
						    response.sendRedirect("success.jsp");
						} else {
						    // If deletion failed, show a pop-up and redirect back to the previous page
						    response.setContentType("text/html");
						    PrintWriter out = response.getWriter();
						    out.println("<script type=\"text/javascript\">");
						    out.println("alert('Failed to delete inventory item');");
						    out.println("history.back();");
						    out.println("</script>");
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
	}

