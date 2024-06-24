<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Inventory</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Update Inventory</h2>
        <form action="UpdateInventoryServlet" method="post" class="form-inline justify-content-center">
            <input type="hidden" name="itemId" value="${item.id}">
            <div class="form-group mx-sm-3 mb-2">
                <label for="stockName" class="sr-only">Stock Name</label>
                <input type="text" class="form-control" id="stockName" name="stockName" placeholder="Stock Name" value="${item.stockName}" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="stockQuantity" class="sr-only">Stock Quantity</label>
                <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" placeholder="Stock Quantity" value="${item.stockQuantity}" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="availableQuantity" class="sr-only">Available Quantity</label>
                <input type="number" class="form-control" id="availableQuantity" name="availableQuantity" placeholder="Available Quantity" value="${item.availableQuantity}" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="dateOfSupply" class="sr-only">Date of Supply</label>
                <input type="date" class="form-control" id="dateOfSupply" name="dateOfSupply" placeholder="Date of Supply" value="${item.dateOfSupply}" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="recentSupplyTrends" class="sr-only">Recent Supply Trends</label>
                <input type="text" class="form-control" id="recentSupplyTrends" name="recentSupplyTrends" placeholder="Recent Supply Trends" value="${item.recentSupplyTrends}" required>
            </div>
            <div class="form-group mx-sm-3 mb-2">
                <label for="minStockLevel" class="sr-only">Minimum Stock Level</label>
                <input type="number" class="form-control" id="minStockLevel" name="minStockLevel" placeholder="Minimum Stock Level" value="${item.minStockLevel}" required>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Update</button>
            <button type="reset" class="btn btn-secondary mb-2 ml-2">Reset</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies (jQuery and Popper.js) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
