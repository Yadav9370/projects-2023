import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        RegisterPojo user = new RegisterPojo();
        user.setUsername(username);
        user.setMobile(mobile);
        user.setPassword(password);

        RegisterDao userDao = new RegisterDao();
        boolean isRegistered = userDao.registerUser(user);

        if (isRegistered) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
