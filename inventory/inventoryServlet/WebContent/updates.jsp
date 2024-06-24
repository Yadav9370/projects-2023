<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Inventory</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Datepicker CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <!-- Font Awesome for calendar icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
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
        .form-group .input-group-text {
            background-color: #fff;
            border-left: 0;
        }
        .form-group .input-group-append .input-group-text {
            border-left: 0;
            border-top-left-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-group .form-control {
            border-right: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="form-container">
                    <h2 class="text-center">Update Inventory</h2>
                    <form action="UpdateServlets" method="post">
                        <div class="form-group">
                            <label for="stockName">Stock Name:</label>
                            <input type="text" class="form-control" id="stockName" name="stockName" required>
                        </div>
                        <div class="form-group">
                            <label for="stockQuantity">Stock Quantity:</label>
                            <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" required>
                        </div>
                        <div class="form-group">
                            <label for="stockAvailability">Stock Availability:</label>
                            <input type="number" class="form-control" id="stockAvailability" name="availableQuantity" required>
                        </div>
                        <div class="form-group">
                            <label for="dateOfSupply">Date of Supply:</label>
                            <div class="input-group date">
                                <input type="text" class="form-control" id="dateOfSupply" name="dateOfSupply" required>
                                <div class="input-group-append">
                                    <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="recentSupplyTrends">Recent Supply Trends:</label>
                            <input type="text" class="form-control" id="recentSupplyTrends" name="recentSupplyTrends" required>
                        </div>
                        <div class="form-group">
                            <label for="minimumStockLevel">Minimum Stock Level:</label>
                            <input type="number" class="form-control" id="minimumStockLevel" name="minimumStockLevel" required>
                        </div>
                        <div class="form-group">
                            <label for="id">ID:</label>
                            <input type="number" class="form-control" id="id" name="id" required>
                        </div>
                        <div class="btn-group">
                            <button type="submit" class="btn btn-primary">Update</button>
                            <button type="reset" class="btn btn-secondary">Reset</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Font Awesome for calendar icon -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"></script>
    <!-- Bootstrap Datepicker JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var dateInput = document.getElementById('dateOfSupply');
            new bootstrap.Datepicker(dateInput, {
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayHighlight: true
            });
        });
    </script>
</body>
</html>
