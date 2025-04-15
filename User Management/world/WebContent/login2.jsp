<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #007bff, #6610f2);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #ffffff;
        }
        .login-container {
            background: #ffffff;
            padding: 30px;
            width: 100%;
            max-width: 400px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .login-container h2 {
            font-weight: 700;
            color: #333;
            text-transform: uppercase;
            text-align: center;
            margin-bottom: 20px;
        }
        .form-label {
            font-weight: 600;
            color: #555;
        }
        .form-control {
            border: 1px solid #ddd;
            border-radius: 6px;
            height: 45px;
        }
        .form-control:focus {
            box-shadow: none;
            border-color: #007bff;
        }
        .btn-primary {
            background: linear-gradient(135deg, #007bff, #6610f2);
            border: none;
            padding: 10px;
            border-radius: 6px;
            font-weight: bold;
        }
        .btn-primary:hover {
            background: linear-gradient(135deg, #6610f2, #007bff);
        }
        .text-center a {
            color: #6610f2;
            text-decoration: none;
            font-weight: 600;
        }
        .text-center a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form id="loginForm" action="LoginServlet" method="POST">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Login</button>
            <div class="text-center mt-3">
                <a href="Register2.jsp">Don't have an account? Register</a>
            </div>
        </form>
    </div>
    <script>
        document.getElementById('loginForm').addEventListener('submit', function (e) {
            const username = document.getElementById('username').value.trim();
            const password = document.getElementById('password').value.trim();
            if (!username || !password) {
                e.preventDefault();
                alert('Both fields are required!');
            }
        });
    </script>
</body>
</html>
