import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class login2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Set the data in the POJO
        LoginPojo user = new LoginPojo();
        user.setUsername(username);
        user.setPassword(password);

        // Validate using DAO
        LoginDao dao = new LoginDao();
        boolean isValid = dao.validateUser(user);

        if (isValid) {
            // Check for specific username and password
            if ("dayasankar".equals(username) && "9928".equals(password)) {
                // Dispatch to admin page
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            } else {
                // Create session and dispatch to the home page
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Dispatch back to the login page with an error message
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp?error=Invalid username or password");
            dispatcher.forward(request, response);
        }
    }
}
