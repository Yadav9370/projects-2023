<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registration Page</title>
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
        .alert {
            margin-bottom: 20px;
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
</head>
<body>
    <div class="container">
        <h2>User Registration</h2>

        <!-- Display error message if available -->
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <!-- Display success message if available -->
        <% if (request.getAttribute("success") != null) { %>
            <div class="alert alert-success" role="alert">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>

        <form action="RegisterServlet" method="post">
            <div class="form-group mb-4">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Enter your username" required>
                
                <!-- Show error message if username is invalid -->
                <% if (request.getAttribute("usernameError") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("usernameError") %>
                    </div>
                <% } %>
            </div>
            
            <div class="form-group mb-4">
                <label for="mobile">Mobile Number:</label>
                <input type="tel" id="mobile" name="mobile" 
                       class="form-control" 
                       pattern="\d{10}" 
                       title="Please enter exactly 10 digits" 
                       placeholder="Enter your mobile number" 
                       maxlength="10" 
                       required>
                
                <!-- Show error message if mobile number is invalid -->
                <% if (request.getAttribute("mobileError") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("mobileError") %>
                    </div>
                <% } %>
            </div>
            
            <div class="form-group mb-4">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" 
                       class="form-control" 
                       placeholder="Enter your password" 
                       required>
            </div>
            
            <button type="submit" class="btn">Register</button>
        </form>
    </div>
    <!-- Include Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
