import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.digests.Blake2bDigest;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@WebServlet("/EncryptionServlet")
@MultipartConfig
public class EncryptionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int SALT_LENGTH = 16;
    private static final int NONCE_LENGTH = 12;
    private static final int TAG_LENGTH = 16;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        Part filePart = request.getPart("file");

        byte[] salt = generateRandomBytes(SALT_LENGTH);
        byte[] nonce = generateRandomBytes(NONCE_LENGTH);
        byte[] key = generateKey();
        byte[] authTag = generateBlake2Hash(filePart.getInputStream());

        EncryptionResult result = encryptAES(filePart.getInputStream(), key, nonce);

        String encryptedFilePath = "D:/encrypted_file.enc";
        try (FileOutputStream fos = new FileOutputStream(encryptedFilePath)) {
            fos.write(result.getEncryptedData());
        }

        String pdfPath = "D:/encrypted_file.pdf";
        createEncryptedPdf(pdfPath, password, encryptedFilePath, key, nonce, salt, authTag);

        if (result != null) {
            request.setAttribute("message", "Encryption completed and parameters saved successfully!");
            request.getRequestDispatcher("decryptqr.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Encryption failed. Please try again.");
            request.getRequestDispatcher("errors.jsp").forward(request, response);
        }
    }

    private byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32]; // AES-256 key length
        random.nextBytes(key);
        return key;
    }

    private EncryptionResult encryptAES(InputStream dataStream, byte[] key, byte[] nonce) throws IOException {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH * 8, nonce);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

            byte[] inputBytes = readInputStream(dataStream);
            byte[] encryptedData = cipher.doFinal(inputBytes);

            return new EncryptionResult(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] generateBlake2Hash(InputStream dataStream) throws IOException {
        Blake2bDigest blake2bDigest = new Blake2bDigest(32);
        byte[] inputBytes = readInputStream(dataStream);
        blake2bDigest.update(inputBytes, 0, inputBytes.length);
        byte[] hash = new byte[blake2bDigest.getDigestSize()];
        blake2bDigest.doFinal(hash, 0);
        return hash;
    }

    private void createEncryptedPdf(String pdfPath, String password, String encryptedFilePath, byte[] key, byte[] nonce, byte[] salt, byte[] authTag) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            writer.setEncryption(password.getBytes(), password.getBytes(),
                PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

            document.open();
            document.add(new Paragraph("Encrypted file content has been saved to: " + encryptedFilePath));
            document.add(new Paragraph("Encryption Key: " + bytesToHex(key)));
            document.add(new Paragraph("Nonce: " + bytesToHex(nonce)));
            document.add(new Paragraph("Salt: " + bytesToHex(salt)));
            document.add(new Paragraph("Authentication Tag: " + bytesToHex(authTag)));
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
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

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private byte[] generateRandomBytes(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return bytes;
    }

    private class EncryptionResult {
        private byte[] encryptedData;

        public EncryptionResult(byte[] encryptedData) {
            this.encryptedData = encryptedData;
        }

        public byte[] getEncryptedData() {
            return encryptedData;
        }
    }
}
