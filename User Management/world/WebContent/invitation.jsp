<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #007bff, #6610f2);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
            font-family: 'Roboto', sans-serif;
        }
        .container {
            width: 100%;
            max-width: 420px;
        }
        .card {
            padding: 25px 30px;
            border-radius: 15px;
            background: #ffffff;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            color: #333;
        }
        .card h2 {
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
            color: #333;
            text-transform: uppercase;
        }
        .form-group label {
            font-weight: 600;
            color: #555;
        }
        .form-control {
            border: 1px solid #ddd;
            border-radius: 8px;
            height: 45px;
        }
        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
        .button-container {
            display: flex;
            justify-content: space-between;
        }
        .btn {
            border-radius: 8px;
            font-weight: bold;
        }
        .btn-primary {
            background: linear-gradient(135deg, #007bff, #6610f2);
            border: none;
        }
        .btn-primary:hover {
            background: linear-gradient(135deg, #6610f2, #007bff);
        }
        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        .message {
            padding: 15px;
            margin-top: 20px;
            border-radius: 8px;
            font-weight: 600;
            text-align: center;
        }
        .message.success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .message.error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        .referral-code {
            font-size: 1.5rem;
            color: #007bff;
            font-weight: bold;
        }
    </style>
    <title>Referral Code</title>
</head>
<body>
    <div class="container">
        <div class="card">
            <h2>Generate Referral Code</h2>
            <form action="InvitationServlet" method="POST">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" class="form-control" required>
                </div>
                <div class="button-container mt-4">
                    <button type="submit" class="btn btn-primary">Generate</button>
                    <a href="home.jsp" class="btn btn-secondary">Back to Home</a>
                </div>
            </form>

            <c:if test="${not empty message}">
                <div class="message success">
                    <p>${message}</p>
                </div>
            </c:if>

            <c:if test="${not empty referralCode}">
                <div class="message">
                    <p>Your Referral Code: <span class="referral-code">${referralCode}</span></p>
                </div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="message error">
                    <p>${error}</p>
                </div>
            </c:if>
        </div>
    </div>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
