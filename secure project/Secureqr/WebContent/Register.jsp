<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        $(function() {
            // Datepicker for Date of Birth
            $("#dob").datepicker({
                dateFormat: 'yy-mm-dd',
                changeMonth: true,
                changeYear: true,
                yearRange: "-100:+0"  // Adjust this range as necessary
            });

            // Clicking calendar icon focuses on date input
            $("#calendarIcon").click(function() {
                $("#dob").focus();
            });

            // Generate Password button functionality
            $("#generatePassword").click(function() {
                var user_id = $("#user_id").val();
                var name = $("#name").val();
                var dob = $("#dob").val();
                
                $.ajax({
                    url: "RegisterServlet",
                    method: "POST",
                    data: { user_id: user_id, name: name, dob: dob },
                    success: function(response) {
                        $("#password").val(response);
                    }
                });
            });

            // Save button functionality
            $("#savePassword").click(function() {
                var hashedPassword = sha512($("#user_id").val() + $("#name").val() + $("#dob").val());
                
                // Simulate file save by creating a Blob and triggering download
                var blob = new Blob([hashedPassword], { type: "text/plain;charset=utf-8" });
                saveAs(blob, "hashed_password.txt");
            });

            // Toggle Password visibility
            $("#togglePassword").click(function() {
                var passwordField = $("#password");
                var type = passwordField.attr("type") === "password" ? "text" : "password";
                passwordField.attr("type", type);
                $(this).find('i').toggleClass('fa-eye fa-eye-slash');
            });
        });

        // Function to hash password using SHA-512 (Client-side hashing for demonstration)
        function sha512(str) {
            var hash = CryptoJS.SHA512(str);
            return hash.toString(CryptoJS.enc.Hex);
        }
    </script>
    <style>
        .password-container {
            display: flex;
            align-items: center;
        }
        .password-container button {
            margin-left: 10px;
        }
        .password-container i {
            margin-left: 5px;
        }
        .calendar-container {
            position: relative;
        }
        .calendar-container .input-group-text {
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Register</h2>
        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label for="user_id">User ID:</label>
                <input type="text" class="form-control" id="user_id" name="user_id" required>
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group calendar-container">
                <label for="dob">Date of Birth:</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="dob" name="dob" required readonly>
                    <div class="input-group-append">
                        <span class="input-group-text" id="calendarIcon">
                            <i class="fas fa-calendar-alt"></i>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                
                <div class="password-container">
                <input type="password" class="form-control" id="password" name="password" required>
                    <button type="button" class="btn btn-secondary" id="togglePassword">
                        <i class="fas fa-eye"></i>
                    </button>
                </div>
            </div>
            <button type="submit" class="btn btn-success">Register</button>
            <a href="login.jsp" class="btn btn-link">Login</a>
        </form>
    </div>
</body>
</html>
