import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.awt.image.BufferedImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import javax.imageio.ImageIO;

@WebServlet("/DecryptQRServlet")
@MultipartConfig
public class DecryptQRServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int TAG_LENGTH = 16; // AES GCM tag length in bytes
    private static final int NONCE_LENGTH = 12; // AES GCM nonce length in bytes

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract parameters from request
            Part filePart = request.getPart("file");
            String keyHex = request.getParameter("key");
            String nonceHex = request.getParameter("nonce");
            String authTagHex = request.getParameter("authTag");
            String saltHex = request.getParameter("salt");
            String userPassword = request.getParameter("password"); // Password from form input

            byte[] key = hexToBytes(keyHex);
            byte[] nonce = hexToBytes(nonceHex);
            byte[] authTag = hexToBytes(authTagHex);
            byte[] salt = hexToBytes(saltHex);

            // Fetch stored password from the database
            DecryptDao decryptDao = new DecryptDao();
            PasswordPojo passwordPojo = decryptDao.getPasswordPojo();

            if (passwordPojo == null) {
                throw new ServletException("No password found in the database.");
            }

            String storedPassword = passwordPojo.getPassword();

            // File paths
            String encryptedFilePath = "D:/encrypted_file.enc";
            String pdfPath = "D:/decrypted_files.pdf";

            // Verify parameters and decrypt the file
            if (verifyParameters(encryptedFilePath, key, nonce, authTag, salt)) {
                String decryptedFilePath = decryptAES(encryptedFilePath, key, nonce);
                generateQRCode(new File(decryptedFilePath), pdfPath, userPassword); // Save QR code in PDF

                request.setAttribute("message", "Decryption and QR code generation completed successfully!");
                request.getRequestDispatcher("successs.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Verification failed. Decryption not performed.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Decryption failed", e);
        }
    }

    private boolean verifyParameters(String encryptedFilePath, byte[] key, byte[] nonce, byte[] authTag, byte[] salt) {
        // Placeholder for parameter verification logic
        return true; // Modify with actual verification logic
    }

    private String decryptAES(String filePath, byte[] key, byte[] nonce) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

        // Temporary decrypted file path
        String decryptedFilePath = "D:/temp_decrypted_file.txt";
        try (FileInputStream fis = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(decryptedFilePath)) {
            byte[] encryptedData = readInputStream(fis);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            fos.write(decryptedData);
        }
        return decryptedFilePath;
    }

    private byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        return buffer.toByteArray();
    }

    private void generateQRCode(File decryptedFile, String pdfPath, String password) throws Exception {
        String fileContent = new String(java.nio.file.Files.readAllBytes(decryptedFile.toPath()));
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(fileContent, com.google.zxing.BarcodeFormat.QR_CODE, 300, 300);

        // Convert BitMatrix to BufferedImage
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Create a temporary file for the QR code image
        File tempFile = File.createTempFile("qr_code", ".png");
        try {
            // Save the BufferedImage to the temporary file
            ImageIO.write(qrCodeImage, "PNG", tempFile);

            // Create and save QR code to PDF
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true, true)) {
                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.newLineAtOffset(25, 700);
                    contentStream.showText("QR Code:");
                    contentStream.endText();

                    // Create PDImageXObject from the temporary file
                    PDImageXObject pdImage = PDImageXObject.createFromFile(tempFile.getAbsolutePath(), document);
                    contentStream.drawImage(pdImage, 25, 500, pdImage.getWidth(), pdImage.getHeight());
                }

                // Set encryption and permissions
                AccessPermission ap = new AccessPermission();
                ap.setCanPrint(true); // Allow printing
                ap.setCanModify(false); // Disallow modifications

                StandardProtectionPolicy policy = new StandardProtectionPolicy(password, password, ap);
                policy.setEncryptionKeyLength(128); // 128-bit encryption
                document.protect(policy);

                document.save(pdfPath);
            }
        } finally {
            // Clean up temporary file
            if (tempFile.exists()) {
                tempFile.delete();
            }
        }
    }

    private static byte[] hexToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have an even length.");
        }
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            int index = i * 2;
            int value = Integer.parseInt(hex.substring(index, index + 2), 16);
            bytes[i] = (byte) value;
        }
        return bytes;
    }
}
