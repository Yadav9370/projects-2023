import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String url = "jdbc:mysql://localhost:3306/secure";
    private static final String username = "root";
    private static final String password = "asdfghjkl";
    private static final String PASSWORD_FILE_PATH = "C:\\Users\\AAKASH";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Retrieve parameters from request
            int userId = Integer.parseInt(request.getParameter("user_id"));
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String password = request.getParameter("password");

            // Create RegisterPojo object
            RegisterPojo user = new RegisterPojo();
            user.setUser_id(userId);
            user.setName(name);
            user.setDob(dob);
            user.setPassword(password); // Plain password from form input

            // Call DAO to insert user
            RegisterDao registerDao = new RegisterDao();
            boolean inserted = registerDao.insertUser(user);

            if (inserted) {
                // Successful insertion
                response.sendRedirect("success.jsp");
            } else {
                // Insertion failed
                response.sendRedirect("error.jsp");
            }
        } catch (NoSuchAlgorithmException | IOException ex) {
            ex.printStackTrace();
            response.sendRedirect("errors.jsp"); // Handle error
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}