<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(to bottom, #007bff, #6610f2);
            font-family: 'Roboto', sans-serif;
            color: #fff;
        }
        .container {
            max-width: 600px;
            margin: 60px auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.25);
            color: #212529;
        }
        h2 {
            text-align: center;
            font-size: 28px;
            font-weight: 600;
            color: #495057;
            margin-bottom: 30px;
        }
        .form-group label {
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 8px;
            display: block;
            color: #495057;
        }
        .form-control {
            border-radius: 8px;
            border: 1px solid #ced4da;
            padding: 10px 15px;
        }
        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.25);
        }
        .error {
            color: #dc3545;
            font-size: 14px;
            margin-bottom: 20px;
            text-align: center;
        }
        .btn {
            display: block;
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            background-color: #007bff;
            color: #fff;
            font-weight: 600;
            border: none;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn:focus {
            box-shadow: 0 0 8px rgba(0, 123, 255, 0.5);
        }
    </style>
    <script>
        function validateMobileNumber(input) {
            const value = input.value.replace(/\D/g, ''); // Remove non-numeric characters
            input.value = value; // Update the field with only numeric characters
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="Register2Servlet" method="post">
            <div class="form-group mb-4">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required>
            </div>

            <div class="form-group mb-4">
                <label for="mobileNumber">Mobile Number:</label>
                <input type="tel" id="mobileNumber" name="mobileNumber" 
                       class="form-control" 
                       pattern="\d{10}" 
                       title="Please enter exactly 10 digits" 
                       placeholder="Enter your mobile number" 
                       maxlength="10" 
                       oninput="validateMobileNumber(this)" 
                       required>
            </div>

            <div class="form-group mb-4">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Enter your password" required>
            </div>

            <div class="form-group mb-4">
                <label for="invitationCode">Invitation Code:</label>
                <input type="text" id="invitationCode" name="invitationCode" class="form-control" placeholder="Enter invitation code" required>
            </div>

            <button type="submit" class="btn">Register</button>
        </form>
    </div>
    <!-- Include Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
