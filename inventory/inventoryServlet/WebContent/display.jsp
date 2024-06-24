<!-- display.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="inventoryServlet.Inventorys" %>
<%@ page import="inventoryServlet.DisplayDAO" %> <!-- Update package name and class name -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory Display</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #333;
            text-align: center;
            margin-top: 20px;
            margin-bottom: 20px; /* Add margin between heading and table */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .notification {
            background-color: #4CAF50; /* Green */
            color: white;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            border-radius: 3px;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>Inventory Display</h2>
    <%-- Display success notifications if any --%>
    <% if (request.getAttribute("successNotification") != null) { %>
        <div class="notification">
            <%= request.getAttribute("successNotification") %>
        </div>
    <% } %>
    <table>
        <thead>
            <tr>
                <th>Stock Name</th>
                <th>Stock Quantity</th>
                <th>Availability Quantity</th>
                <th>Date of Supply</th>
                <th>Recent Supply Trends</th>
                <th>Minimum Stock Trends</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                DisplayDAO displayDAO = new DisplayDAO(); 
                List<Inventorys> inventoryItems = displayDAO.getAllItems(); 
                for (Inventorys item : inventoryItems) {
            %>
            <tr>
                <td><%= item.getStockName() %></td>
                <td><%= item.getStockQuantity() %></td>
                <td><%= item.getAvailabilityQuantity() %></td>
                <td><%= item.getDateOfSupply() %></td>
                <td><%= item.getRecentSupplyTrends() %></td>
                <td><%= item.getMinimumStockTrends() %></td>
                <td>
                    <form action="RefreshServlet" method="post">
                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                        <button type="submit">Refresh</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
