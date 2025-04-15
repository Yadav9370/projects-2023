import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Register2Servlet extends HttpServlet {
    private Register2Dao userDAO;
    
    public void init() {
        userDAO = new Register2Dao();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String mobileNumber = request.getParameter("mobileNumber");
        String password = request.getParameter("password");
        String invitationCode = request.getParameter("invitationCode");
        
        // Check if username or mobile already exists
        if (Register2Dao.isUserExists(username, mobileNumber)) {
            request.setAttribute("error", "Username or mobile number already exists!");
            request.getRequestDispatcher("Register2.jsp").forward(request, response);
            return;
        }
        
        // Create user object
        Register2Pojo user = new Register2Pojo();
        user.setUsername(username);
        user.setMobileNumber(mobileNumber);
        user.setPassword(password);
        user.setInvitationCode(invitationCode);
        
        // Validate invitation code
        if (!Register2Dao.isValidInvitation(invitationCode)) {
            request.setAttribute("error", "Invalid invitation link!");
            request.getRequestDispatcher("Register2.jsp").forward(request, response);
            return;
        }
        
        // Attempt to register user
        if (Register2Dao.registerUser(user)) {
            response.sendRedirect("login2.jsp");
        } else {
            request.setAttribute("error", "Registration failed!");
            request.getRequestDispatcher("Register2.jsp").forward(request, response);
        }
    }
}