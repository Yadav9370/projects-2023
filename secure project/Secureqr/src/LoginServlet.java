import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the login form
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // Create LoginPojo object with user input
        LoginPojo user = new LoginPojo();
        user.setName(name);
        user.setPassword(password);

        // Instantiate LoginDao to perform database operations
        LoginDao loginDao = new LoginDao();

        // Check if the user exists and credentials are valid
        boolean isValidUser = loginDao.validateUser(user);

        if (isValidUser) {
            // Insert user data into database
            boolean inserted = loginDao.insertUser(user);

            if (inserted) {
                // Store user information in session along with created_at timestamp
                HttpSession session = request.getSession();
                session.setAttribute("name", user.getName());
                session.setAttribute("created_at", user.getCreated_at());

                // Forward to Home.jsp upon successful insertion
                request.getRequestDispatcher("Home.jsp").forward(request, response);
            } else {
                // Insertion failed, forward to error.jsp
                request.getRequestDispatcher("Home.jsp").forward(request, response);
            }
        } else {
            // Validation failed, forward to error.jsp
            request.getRequestDispatcher("er.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
