<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Inventory Show</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom CSS for table */
        body {
            background-color: #f8f9fa; /* Light gray background */
        }
        .container {
            margin-top: 50px; /* Add space from top */
        }
        th {
            background-color: #007bff; /* Blue header background */
            color: white; /* White text color */
        }
        td {
            background-color: #ffffff; /* White cell background */
        }
        .button-container {
            text-align: center;
        }

        .button-container a {
            display: inline-block;
            margin: 0 10px;
        }

        .navbar-brand img {
            height: 40px;
            width: auto;
        }
        .profile-menu {
            position: relative;
            display: inline-block;
        }
        .profile-dropdown {
            display: none;
            position: absolute;
            right: 0;
            background-color: white;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        .profile-dropdown a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .profile-dropdown a:hover {
            background-color: #f1f1f1;
        }
        .profile-menu:hover .profile-dropdown {
            display: block;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">
        <img src="https://static.vecteezy.com/system/resources/previews/000/176/200/original/vector-abstract-company-logo-template-design-illustration.jpg" alt="Company Logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="Home.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="InventoryShow.jsp">Inventory</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Product.jsp">Product</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="SalesShow.jsp">Sales</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="CustomerShow.jsp">Customer</a>
            </li>
        </ul>
        <div class="profile-menu">
            <img src="profile-icon.png" alt="Profile" class="navbar-brand">
            <div class="profile-dropdown">
                <a href="thankyou.jsp">Logout</a>
            </div>
        </div>
    </div>
</nav>


<div class="container mt-5">
    <h2 class="text-center">Inventory</h2>
    <br>
    <div class="button-container">
        <a href="Inventory.jsp" class="btn btn-primary">Insert Stock</a>
        <a href="InventoryDelete.jsp" class="btn btn-secondary">Delete Stock</a>
        <a href="updates.jsp" class="btn btn-secondary">Update Item</a>
    </div>
</div>
<br>
<div class="container">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Available Quantity</th>
            <th>Days of Supply</th>
            <th>Recent Sales Trend</th>
            <th>Minimum Stock Level</th>
        </tr>
        </thead>
        <tbody>
            <% 
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet rs = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdacproject", "root", "asdfghjkl");
                    preparedStatement = connection.prepareStatement("SELECT * FROM inventorytable");
                    rs = preparedStatement.executeQuery();
                    while (rs.next()) {
            %>
            <tr>
                 <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("stockName") %></td>
                <td><%= rs.getInt("stockQuantity") %></td>
                <td><%= rs.getInt("availableQuantity") %></td>
                <td><%= rs.getDate("dateOfSupply") %></td>
                <td><%= rs.getString("recentSupplyTrends") %></td>
                <td><%= rs.getInt("minStockLevel") %></td>
            </tr>
            <% 
                    }
                }  catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (preparedStatement != null) preparedStatement.close();
                        if (connection != null) connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS and dependencies (jQuery and Popper.js) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
