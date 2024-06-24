

import inventoryServlet.SignUpDAO;
import inventoryServlet.SignUpPojo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private SignUpDAO signUpDAO; // Updated from UserDAO

	    public void init() {
	        signUpDAO = new SignUpDAO(); // Updated from UserDAO
	    }
    public SignUpController() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        SignUpPojo user = new SignUpPojo();
	        user.setUsername(username);
	        user.setEmail(email);
	        user.setPassword(password);

	        try {
	            if (signUpDAO.addUser(user)) { // Updated from UserDAO
	                // Sign-up successful, dispatch to login.jsp
	                request.getRequestDispatcher("login.jsp").forward(request, response);
	            } else {
	                // Sign-up failed, dispatch to error.jsp
	                request.getRequestDispatcher("error.jsp").forward(request, response);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle database error
	            // You may redirect to an error page or show an error message
	            request.getRequestDispatcher("error.jsp").forward(request, response);
	        }
	}

}
