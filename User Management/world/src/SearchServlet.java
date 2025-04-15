import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String searchTerm = request.getParameter("invitationCode");
        
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            try {
                // Create SearchDao object
                SearchDao userDAO = new SearchDao();
                
                // Fetch users by search term
                List<SearchPojo> users = userDAO.searchByTerm(searchTerm);
                
                // Set the result to request attributes
                request.setAttribute("users", users);
               
            } catch (Exception e) {
                request.setAttribute("error", "An error occurred during search");
                e.printStackTrace();
            }
        }
        
        // Forward to the JSP page to display results
        request.getRequestDispatcher("/Search.jsp").forward(request, response);
    }
}