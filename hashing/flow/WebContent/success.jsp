<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Decryption Successful</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Decryption Successful!</h2>

        <p>${message}</p>

        <table class="table table-bordered">
            <tr>
                <th>RSA Decryption Time (ms)</th>
                <td>${rsaDecryptionTime}</td>
            </tr>
           
            <tr>
                <th>File Decryption Using the AES Take Time (ns)</th>
                <td>${aesDecryptionTimeNanos}</td>
            </tr>
            <tr>
                <th>Merkle Validation Time (ns)</th>
                <td>${merkleValidationTimeNanos}</td>
            </tr>
        </table>

        <p><a href="index.jsp" class="btn btn-primary">Go Back to Home</a></p>
    </div>
</body>
</html>
