import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdInsertServlet extends HttpServlet {
    private InsertDao adDAO;
    
    public void init() {
        adDAO = new InsertDao();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        
        try {
            adDAO.insertAd(username);
            response.sendRedirect("/AdminServlet?");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
