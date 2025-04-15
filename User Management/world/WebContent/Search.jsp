<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Search Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
            padding: 20px;
        }

        .search-container {
            background: #ffffff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            padding: 30px;
            margin: 20px auto;
            max-width: 800px;
        }

        .search-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .search-header h2 {
            font-size: 24px;
            font-weight: 600;
            color: #007bff;
        }

        .search-box {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .search-input {
            padding: 10px;
            width: 300px;
            border: 1px solid #ced4da;
            border-radius: 5px;
            outline: none;
        }

        .search-input:focus {
            border-color: #007bff;
            box-shadow: 0 0 3px rgba(0, 123, 255, 0.5);
        }

        .search-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 10px;
            transition: background-color 0.3s ease;
        }

        .search-button:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 15px;
            border: 1px solid #dee2e6;
            text-align: left;
            font-size: 14px;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        tr:hover {
            background-color: #e9ecef;
        }
    </style>
</head>
<body>
    <div class="search-container">
        <div class="search-header">
            <h2>Search Users by Invitation Code</h2>
        </div>
        <div class="search-box">
            <form action="SearchServlet" method="get">
                <input type="text" name="invitationCode" placeholder="Enter invitation code" 
                       class="search-input" value="${param.invitationCode}">
                <button type="submit" class="search-button">
                    🔍 Search
                </button>
            </form>
        </div>

        <c:if test="${not empty users}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Invitation Code</th>
                        <th>Designation</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.invitation}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${status.index == 0}">
                                        Owner
                                    </c:when>
                                    <c:otherwise>
                                        Joiner
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>

    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>