<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Decrypt QR Code</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f8f9fa;
        }
        .container {
            width: 50%;
            max-width: 600px;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .btn-primary {
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Decrypt QR Code</h2>

        <form action="DecryptQRServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="fileInput">Upload Encrypted File:</label>
                <input type="file" class="form-control-file" id="fileInput" name="fileInput" required>
            </div>
            <div class="form-group">
                <label for="keyInput">Encryption Key:</label>
                <input type="text" class="form-control" id="keyInput" name="key" required>
            </div>
            <div class="form-group">
                <label for="nonceInput">Nonce:</label>
                <input type="text" class="form-control" id="nonceInput" name="nonce" required>
            </div>
            <div class="form-group">
                <label for="saltInput">Salt:</label>
                <input type="text" class="form-control" id="saltInput" name="salt" required>
            </div>
            <div class="form-group">
                <label for="authTagInput">Authentication Tag:</label>
                <input type="text" class="form-control" id="authTagInput" name="authTag" required>
            </div>
             <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Decrypt QR Code</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
