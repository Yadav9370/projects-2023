import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/InvitationServlet")
public class InvitationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        InvitationPojo invitation = new InvitationPojo();
        invitation.setUsername(username);

        InvitationDao dao = new InvitationDao();

        if (dao.verifyUsername(username)) {
            // Check if the referral code already exists
            String existingReferralCode = dao.getReferralCode(username);

            if (existingReferralCode == null) {
                // Generate a shorter unique referral code using a substring of UUID
                String referralCode = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
                invitation.setInvitation(referralCode);  // Set the generated referral code

                // Save the referral code into the database
                if (dao.saveInvitation(username, referralCode)) {
                    request.setAttribute("message", "Referral code created successfully!");
                    request.setAttribute("referralCode", referralCode);  // Pass the new referral code to the JSP
                } else {
                    request.setAttribute("error", "Failed to save referral code.");
                }
            } else {
                // Referral code already exists
                request.setAttribute("message", "Referral code already exists.");
                request.setAttribute("referralCode", existingReferralCode);  // Pass the existing referral code to the JSP
            }
        } else {
            request.setAttribute("error", "Invalid username.");
        }

        // Forward to the same JSP page
        request.getRequestDispatcher("invitation.jsp").forward(request, response);
    }
}
