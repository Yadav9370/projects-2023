<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Decryption Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS for styling -->
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        .card {
            padding: 20px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">File Decryption</h2>
        
        <!-- Form for decryption -->
        <form action="DecryptionServlet" method="post" enctype="multipart/form-data">
            <div class="card">
                <div class="card-body">
                    <!-- File Upload -->
                    <div class="mb-3">
                        <label for="encryptedFile" class="form-label">Choose Encrypted File</label>
                        <input type="file" class="form-control" id="encryptedFile" name="encryptedFile" required>
                    </div>

                    <!-- File Key Upload -->
                    <div class="mb-3">
                        <label for="encryptedKey" class="form-label">Encrypted Key</label>
                        <input type="file" class="form-control" id="encryptedKey" name="encryptedKey" required>
                    </div>
                    
                    <!-- Private Key Upload -->
                    <div class="mb-3">
                        <label for="privateKey" class="form-label">Private Key</label>
                        <input type="file" class="form-control" id="privateKey" name="privateKey" required>
                    </div>

                    <!-- Merkle Root Input -->
                    <div class="mb-3">
                        <label for="merkleRoot" class="form-label">Merkle Root</label>
                        <input type="text" class="form-control" id="merkleRoot" name="merkleRoot" required>
                    </div>
                    
                    <!-- Hash Input -->
                    <div class="mb-3">
                        <label for="hash" class="form-label">Enter Hash</label>
                        <input type="text" class="form-control" id="hash" name="hash" required>
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-primary">Decrypt File</button>
                </div>
            </div>
        </form>

        <!-- Result or error message -->
        <c:if test="${not empty decryptionSuccess}">
            <div class="alert alert-info mt-3" id="decryptionResult">
                File decrypted successfully! The file has been saved to the server.
                <br>
                <strong>Decryption Time:</strong> ${decryptionTime} ms<br>
                <strong>Verification Time:</strong> ${verificationTime} ms
            </div>
        </c:if>

        <c:if test="${not empty decryptionError}">
            <div class="alert alert-danger mt-3" id="decryptionError">
                An error occurred during decryption. Please try again.
            </div>
        </c:if>
    </div>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
