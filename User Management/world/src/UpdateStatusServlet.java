import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Map<String, Boolean> jsonResponse = new HashMap<>();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "UPDATE users SET status = 'yes' WHERE username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, request.getParameter("username"));
            
            int result = pstmt.executeUpdate();
            jsonResponse.put("success", result > 0);
            
        } catch(Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
        } finally {
            DatabaseUtil.closeResources(conn, pstmt, null);
        }
        
        out.println(new Gson().toJson(jsonResponse));
    }
}