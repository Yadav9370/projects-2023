<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: green;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .card {
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="card shadow-lg" style="width: 400px;">
        <div class="card-body">
            <h2 class="text-center text-primary">Registration</h2>
            <form action="RegisterServlet" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter your username" required>
                </div>
                <div class="form-group">
                    <label for="mobile">Mobile Number:</label>
                    <input type="text" class="form-control" id="mobile" name="mobile" 
                           placeholder="Enter your mobile number" 
                           required pattern="\d{10}" 
                           title="Enter a valid 10-digit mobile number">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Register</button>
            </form>
        </div>
    </div>
    <script>
        // Additional validation to ensure only 10 digits
        document.getElementById('mobile').addEventListener('input', function (event) {
            const mobileInput = event.target;
            const isValid = /^\d{0,10}$/.test(mobileInput.value);
            if (!isValid) {
                mobileInput.value = mobileInput.value.slice(0, -1);
            }
        });
    </script>
</body>
</html>
