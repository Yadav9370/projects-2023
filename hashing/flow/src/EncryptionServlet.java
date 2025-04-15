import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.bouncycastle.crypto.digests.Blake3Digest;
import org.bouncycastle.util.encoders.Hex;

@MultipartConfig
public class EncryptionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the uploaded file part
            Part filePart = request.getPart("file");

            // Validate file type
            String fileName = filePart.getSubmittedFileName();
            if (fileName == null || !fileName.endsWith(".txt")) {
                // If the file is not a .txt file, forward to errors.jsp
                request.setAttribute("errorMessage", "Only .txt files are allowed.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("errors.jsp");
                dispatcher.forward(request, response);
                return; // Stop further processing
            }

            InputStream fileContent = filePart.getInputStream();
            byte[] fileBytes = toByteArray(fileContent);

            // Compute Merkle root
            long startMerkleTime = System.nanoTime();
            byte[] merkleRoot = computeMerkleRootWithBlake3(fileBytes);
            long endMerkleTime = System.nanoTime();
            System.out.println("Merkle root computation time: " + (endMerkleTime - startMerkleTime) + " nanoseconds");

            // Hash the Merkle root
            long startHashTime = System.nanoTime();
            byte[] blake3Hash = hashWithBlake3(merkleRoot);
            long endHashTime = System.nanoTime();
            System.out.println("Blake3 hash computation time: " + (endHashTime - startHashTime) + " nanoseconds");

            // Generate AES key
            long startAESGenTime = System.nanoTime();
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey aesKey = keyGen.generateKey();
            long endAESGenTime = System.nanoTime();
            System.out.println("AES key generation time: " + (endAESGenTime - startAESGenTime) + " nanoseconds");

            // Encrypt AES key with RSA public key
            KeyPair rsaKeyPair = generateRSAKeyPair();
            PublicKey publicKey = rsaKeyPair.getPublic();
            PrivateKey privateKey = rsaKeyPair.getPrivate();
            long startRSAEncryptTime = System.nanoTime();
            byte[] encryptedAESKey = encryptWithRSA(aesKey.getEncoded(), publicKey);
            long endRSAEncryptTime = System.nanoTime();
            System.out.println("RSA encryption time: " + (endRSAEncryptTime - startRSAEncryptTime) + " nanoseconds");

            // Encrypt the file using AES
            long startFileEncryptTime = System.nanoTime();
            byte[] encryptedFile = encryptWithAES(fileBytes, aesKey);
            long endFileEncryptTime = System.nanoTime();
            System.out.println("File encryption time: " + (endFileEncryptTime - startFileEncryptTime) + " nanoseconds");

            // Save output files
            String outputDir = "D:\\";
            saveToFile(outputDir + "encrypted_file.dat", encryptedFile);
            saveToFile(outputDir + "encrypted_key.dat", encryptedAESKey);
            saveToFile(outputDir + "merkle_root.txt", Hex.toHexString(merkleRoot));
            saveToFile(outputDir + "hash.txt", Hex.toHexString(blake3Hash));
            saveToFile(outputDir + "private_key.der", privateKey.getEncoded());
            saveToFile(outputDir + "public_key.der", publicKey.getEncoded());

            // Set success message and forward to Decryption.jsp
            request.setAttribute("successMessage", "Encryption successful!");
            request.setAttribute("outputDirectory", outputDir);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Decryption.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // Handle exceptions and forward to errors.jsp
            request.setAttribute("errorMessage", "Encryption failed: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("errors.jsp");
            dispatcher.forward(request, response);
        }
    }

    private byte[] computeMerkleRootWithBlake3(byte[] fileBytes) {
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

        return hashes.get(0);
    }

    private byte[] hashWithBlake3(byte[] data) {
        Blake3Digest blake3 = new Blake3Digest(32);
        blake3.update(data, 0, data.length);
        byte[] hash = new byte[32];
        blake3.doFinal(hash, 0);
        return hash;
    }

    private byte[] encryptWithAES(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher.doFinal(data);
    }

    private KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    private byte[] encryptWithRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    private void saveToFile(String path, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(data);
        }
    }

    private void saveToFile(String path, String data) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(data);
        }
    }

    private byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int nRead;
        while ((nRead = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }
}
