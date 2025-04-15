import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/free",
                "root",
                "asdfghjkl"
            );
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            List<Map<String, String>> usersList = new ArrayList<>();
            while(rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("username", rs.getString("username"));
                user.put("mobile", rs.getString("mobile"));
                user.put("password", rs.getString("password"));
                usersList.add(user);
            }
            
            request.setAttribute("users", usersList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Show.jsp");
            dispatcher.forward(request, response);
            
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}