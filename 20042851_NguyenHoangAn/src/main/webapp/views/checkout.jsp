<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Checkout - Toy Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Comic Sans MS', 'Arial', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 10px;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        header {
            background-color: #FF5733;
            color: white;
            padding: 10px 0;
            text-align: center;
            margin-bottom: 20px;
            border-radius: 10px;
        }
        .header h1 {
            color: black; /* Set "Checkout" text color to black */
        }
        .header p {
            color: white;
        }
        footer {
            background-color: #FF5733;
            color: white;
            padding: 10px 0;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
        .form-group label {
            font-weight: bold;
        }
        .btn-primary {
            background-color: #FF5733;
            border-color: #FF5733;
        }
        .btn-primary:hover {
            background-color: #E04E2C;
            border-color: #E04E2C;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="Home.jsp">Home</a>
    </nav>
    <header>
        <div class="container header">
            <h1>Checkout</h1>
            <p>Complete your order for the best toys!</p>
        </div>
    </header>
    <div class="container">
        <form action="/place-order" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" class="form-control" rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="paymentMethod">Payment Method:</label>
                <select id="paymentMethod" name="paymentMethod" class="form-control" required>
                    <option value="creditCard">Credit Card</option>
                    <option value="paypal">PayPal</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Place Order</button>
        </form>
    </div>

    <footer>
        &copy; 2024 Toy Store. All rights reserved.
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
