import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import org.bouncycastle.crypto.digests.Blake3Digest;
import org.bouncycastle.util.encoders.Hex;

@MultipartConfig
public class DecryptionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            // Start time for RSA Decryption
            long startTime = System.nanoTime(); 
            
            Part encryptedFilePart = request.getPart("encryptedFile");
            Part encryptedKeyPart = request.getPart("encryptedKey");
            Part privateKeyPart = request.getPart("privateKey"); 
            String transmittedMerkleRoot = request.getParameter("merkleRoot");
            String transmittedHash = request.getParameter("hash");

            byte[] encryptedFile = readInputStreamToByteArray(encryptedFilePart.getInputStream());
            byte[] encryptedKey = readInputStreamToByteArray(encryptedKeyPart.getInputStream());
            byte[] privateKeyBytes = readInputStreamToByteArray(privateKeyPart.getInputStream());

            PrivateKey privateKey = loadPrivateKey(privateKeyBytes);
            
            byte[] symmetricKeyBytes = decryptWithRSA(encryptedKey, privateKey);
            SecretKey symmetricKey = new SecretKeySpec(symmetricKeyBytes, "AES");

            long rsaDecryptionTime = System.nanoTime() - startTime;

            // Start time for AES Decryption
            startTime = System.nanoTime(); 
            byte[] decryptedData = decryptWithAES(encryptedFile, symmetricKey);
            long aesDecryptionTime = System.nanoTime() - startTime;

            // Start time for Merkle Validation
            startTime = System.nanoTime(); 
            byte[] recomputedMerkleRoot = computeMerkleRootWithBlake3(decryptedData);

            if (!Hex.toHexString(recomputedMerkleRoot).equals(transmittedMerkleRoot)) {
                throw new SecurityException("Merkle Root mismatch. Data integrity check failed.");
            }

            byte[] recomputedHash = hashWithBlake3(recomputedMerkleRoot);
            if (!Hex.toHexString(recomputedHash).equals(transmittedHash)) {
                throw new SecurityException("Hash mismatch. Data authenticity check failed.");
            }

            long merkleValidationTime = System.nanoTime() - startTime;

            String outputPath = "D:\\decrypted_file.dat";
            saveToFile(outputPath, decryptedData);

            request.setAttribute("message", "Decryption successful! File saved to: " + outputPath);
            request.setAttribute("rsaDecryptionTime", rsaDecryptionTime / 1_000_000); // Convert to milliseconds
            request.setAttribute("aesDecryptionTimeMillis", aesDecryptionTime / 1_000_000); // Convert to milliseconds
            request.setAttribute("aesDecryptionTimeNanos", aesDecryptionTime); // In nanoseconds
            request.setAttribute("merkleValidationTime", merkleValidationTime / 1_000_000); // Convert to milliseconds
            request.setAttribute("merkleValidationTimeNanos", merkleValidationTime); // In nanoseconds

            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Decryption failed: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    private PrivateKey loadPrivateKey(byte[] privateKeyBytes) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    private byte[] decryptWithRSA(byte[] data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    private byte[] decryptWithAES(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[16]; 
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher.doFinal(data);
    }

    private byte[] computeMerkleRootWithBlake3(byte[] fileBytes) {
        long startTime = System.nanoTime(); // Start time for Merkle Root computation

        int chunkSize = 1024; 
        List<byte[]> hashes = new ArrayList<>();

        for (int i = 0; i < fileBytes.length; i += chunkSize) {
            int end = Math.min(fileBytes.length, i + chunkSize);
            byte[] chunk = Arrays.copyOfRange(fileBytes, i, end);

            Blake3Digest blake3 = new Blake3Digest(32); 
            blake3.update(chunk, 0, chunk.length);
            byte[] hash = new byte[32];
            blake3.doFinal(hash, 0);

            hashes.add(hash);
        }

        while (hashes.size() > 1) {
            List<byte[]> newHashes = new ArrayList<>();
            for (int i = 0; i < hashes.size(); i += 2) {
                byte[] left = hashes.get(i);
                byte[] right = (i + 1 < hashes.size()) ? hashes.get(i + 1) : left; 

                byte[] combined = new byte[left.length + right.length];
                System.arraycopy(left, 0, combined, 0, left.length);
                System.arraycopy(right, 0, combined, left.length, right.length);

                Blake3Digest blake3 = new Blake3Digest(32);
                blake3.update(combined, 0, combined.length);
                byte[] parentHash = new byte[32];
                blake3.doFinal(parentHash, 0);

                newHashes.add(parentHash);
            }
            hashes = newHashes;
        }

        long endTime = System.nanoTime(); // End time for Merkle Root computation
        long merkleValidationTime = endTime - startTime; // Time taken for Merkle Validation in nanoseconds

        return hashes.get(0); 
    }

    private byte[] hashWithBlake3(byte[] data) {
        Blake3Digest blake3 = new Blake3Digest(32); 
        blake3.update(data, 0, data.length);
        byte[] hash = new byte[32];
        blake3.doFinal(hash, 0);
        return hash;
    }

    private void saveToFile(String path, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }
    }

    private byte[] readInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] temp = new byte[1024]; 
        int bytesRead;
        while ((bytesRead = inputStream.read(temp)) != -1) {
            buffer.write(temp, 0, bytesRead);
        }
        return buffer.toByteArray();
    }
} 
