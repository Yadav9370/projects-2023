import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class RegisterDao {
    private static final String URL = "jdbc:mysql://localhost:3306/secure";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "asdfghjkl";
    private static final String PASSWORD_FILE_PATH = "C:\\Users\\AAKASH\\"; // Base path for saving files

    public boolean insertUser(RegisterPojo user) throws NoSuchAlgorithmException {
        boolean inserted = false;
        Connection conn = null;
        PreparedStatement stmtUser = null;
        PreparedStatement stmtUserDetails = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Generate hashed password using MD5 algorithm
            String hashedPassword = hashMD5(user.getPassword());

            // SQL query to insert user data into users table
            String sqlUser = "INSERT INTO users (user_id, name, dob, password) VALUES (?, ?, ?, ?)";
            stmtUser = conn.prepareStatement(sqlUser);
            stmtUser.setInt(1, user.getUser_id());
            stmtUser.setString(2, user.getName());
            stmtUser.setString(3, user.getDob());
            stmtUser.setString(4, hashedPassword);

            // Execute the user table query
            int rowsInsertedUser = stmtUser.executeUpdate();

            // If both inserts are successful, set inserted to true
            if (rowsInsertedUser > 0) {
                inserted = true;

                // Save hashed password to a password-protected PDF file
                saveHashedPasswordAsPdf(user.getUser_id(), hashedPassword);
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Close connections
            try {
                if (stmtUser != null) stmtUser.close();
                if (stmtUserDetails != null) stmtUserDetails.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inserted;
    }

    private void saveHashedPasswordAsPdf(int userId, String hashedPassword) throws IOException {
        String fileName = PASSWORD_FILE_PATH + userId + ".pdf";
        File file = new File(fileName);

        // Create a new document
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.COURIER, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("User ID: " + userId);
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText("Hashed Password: " + hashedPassword);
                contentStream.endText();
            }

            // Protect the document with a password
            AccessPermission accessPermission = new AccessPermission();
            StandardProtectionPolicy policy = new StandardProtectionPolicy(String.valueOf(userId), String.valueOf(userId), accessPermission);
            policy.setEncryptionKeyLength(128);
            policy.setPermissions(accessPermission);
            document.protect(policy);

            document.save(file);
        }
    }

    private String hashMD5(String password) throws NoSuchAlgorithmException {
        // Check for null or empty password
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        // Create MD5 hash of the password
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
