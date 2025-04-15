import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        RegisterDao registerDao = new RegisterDao();
        String username = request.getParameter("username");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        // Check if username already exists
        if (registerDao.isUsernameExists(username)) {
            request.setAttribute("usernameError", "Username already exists. Please choose a different username.");
        } 

        // Check if mobile number already exists
        if (registerDao.isMobileExists(mobile)) {
            request.setAttribute("mobileError", "Mobile number already exists. Please choose a different number.");
        }

        // If there are errors, forward back to registration page
        if (registerDao.isUsernameExists(username) || registerDao.isMobileExists(mobile)) {
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        // Proceed with registration if no errors
        boolean isRegistered = registerDao.registerUser(username, mobile, password);
        if (isRegistered) {
            request.setAttribute("success", "Registration successful! You can now log in.");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
        }

        // Forward to the registration page with appropriate messages
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
