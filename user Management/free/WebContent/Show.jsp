<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f1f3f5;
            font-family: 'Arial', sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .table-container {
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #343a40;
            margin-bottom: 20px;
            font-weight: bold;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .no-data {
            text-align: center;
            color: #6c757d;
            font-size: 16px;
        }
        .form-container {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="form-container">
            <form action="UserServlet" method="get">
                <div class="input-group">
                    <input type="text" class="form-control" name="filter" placeholder="Search by Username or Mobile" />
                    <button class="btn btn-primary" type="submit">Search</button>
                </div>
            </form>
        </div>

        <div class="table-container">
            <h2 class="text-center">User List</h2>
            <table class="table table-bordered table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Username</th>
                        <th>Mobile</th>
                        <th>Password</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty users}">
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.username}</td>
                                <td>${user.mobile}</td>
                                <td>${user.password}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty users}">
                        <tr>
                            <td colspan="3" class="no-data">No users found</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
