<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #495057;
        }
        .container {
            margin-top: 50px;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            padding: 20px;
        }
        .card-body {
            padding: 30px;
            background-color: #ffffff;
            border-bottom-left-radius: 10px;
            border-bottom-right-radius: 10px;
        }
        h3 {
            font-size: 28px;
            font-weight: 600;
        }
        .btn {
            border-radius: 8px;
            font-weight: 600;
            padding: 10px 20px;
        }
        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .table {
            font-size: 16px;
        }
        .table th {
            background-color: #343a40;
            color: white;
            font-weight: 600;
        }
        .table td {
            vertical-align: middle;
        }
        .table-hover tbody tr:hover {
            background-color: #f1f1f1;
        }
        .table-responsive {
            margin-top: 20px;
        }
        .action-btn {
            font-size: 14px;
            padding: 6px 12px;
            text-transform: uppercase;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h3 class="mb-0">User List</h3>
            </div>
            <div class="card-body">
                <form action="UserServlet" method="get">
                    <button type="submit" class="btn btn-success mb-4">
                        <i class="fas fa-users"></i> Show Users
                    </button>
                </form>

                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Mobile</th>
                                <th>Password</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr id="row-${user.username}">
                                    <td>${user.username}</td>
                                    <td>${user.mobile}</td>
                                    <td>${user.password}</td>
                                    <td>
                                        <button 
                                            type="button" 
                                            class="btn ${user.status == 'yes' ? 'btn-success' : 'btn-primary'} btn-sm action-btn" 
                                            data-username="${user.username}" 
                                            data-status="${user.status}">
                                            ${user.status == 'yes' ? 'Yes' : '+'}
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    document.addEventListener("click", function(event) {
        if (event.target.classList.contains("action-btn")) {
            var button = event.target;
            var username = button.getAttribute("data-username");
            var status = button.getAttribute("data-status");

            if (status !== "yes") {
                fetch("UpdateStatusServlet", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: "username=" + encodeURIComponent(username)
                })
                .then(function(response) {
                    return response.json();
                })
                .then(function(result) {
                    if (result.success) {
                        button.textContent = "Yes";
                        button.setAttribute("data-status", "yes");
                        button.classList.remove("btn-primary");
                        button.classList.add("btn-success");
                    } else {
                        alert("Failed to update status.");
                    }
                });
            }
        }
    });
    </script>
</body>
</html>
