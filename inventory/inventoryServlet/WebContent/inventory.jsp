<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inventory Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 50px;
        }
        .form-container {
            background-color: #f9f9f9;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btn-group button {
            display: inline-block;
            margin-right: 10px; /* Adjust margin as needed */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="form-container">
                    <h2 class="text-center">Inventory Management</h2>
                    <form action="InventoryServlets" method="post">
                        <div class="form-group">
                            <label for="stockName">Stock Name</label>
                            <input type="text" class="form-control" id="stockName" name="stockName" required>
                        </div>
                        <div class="form-group">
                            <label for="stockQuantity">Stock Quantity</label>
                            <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" required>
                        </div>
                        <div class="form-group">
                            <label for="availableQuantity">Available Quantity</label>
                            <input type="number" class="form-control" id="availableQuantity" name="availableQuantity" required>
                        </div>
                        <div class="form-group">
                            <label for="dateOfSupply">Date of Supply</label>
                            <input type="date" class="form-control" id="dateOfSupply" name="dateOfSupply" required>
                        </div>
                        <div class="form-group">
                            <label for="recentSupplyTrends">Recent Supply Trends</label>
                            <textarea class="form-control" id="recentSupplyTrends" rows="3" name="recentSupplyTrends" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="minStockLevel">Minimum Stock Level</label>
                            <input type="number" class="form-control" id="minStockLevel" name="minStockLevel" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
