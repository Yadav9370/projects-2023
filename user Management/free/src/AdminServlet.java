import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establishing connection
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/free",
                "root",
                "asdfghjkl"
            );
            
            // Creating statement and executing query
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT username, mobile, password, status FROM users");
            
            // Processing result set
            List<Map<String, String>> usersList = new ArrayList<>();
            while (rs.next()) {
                Map<String, String> user = new HashMap<>();
                user.put("username", rs.getString("username"));
                user.put("mobile", rs.getString("mobile"));
                user.put("password", rs.getString("password"));
                user.put("status", rs.getString("status"));
                usersList.add(user);
            }
            
            // Setting the users list as a request attribute
            request.setAttribute("users", usersList);
            
            // Forwarding to admin.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            // Closing resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
