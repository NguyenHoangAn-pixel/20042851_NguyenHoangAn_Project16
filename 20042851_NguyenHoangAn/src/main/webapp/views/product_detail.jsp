<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Product Detail - Toy Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Comic Sans MS', 'Arial', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 10px;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        nav {
            background-color: #FF5733;
            color: white;
            padding: 10px 0;
            text-align: center;
            margin-bottom: 20px;
        }
        nav a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
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
        .product-image {
            max-height: 400px;
            object-fit: cover;
            border-radius: 10px;
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
        <a class="navbar-brand" href="product_list.jsp">Products</a>
        <a class="navbar-brand" href="cart.jsp">Cart</a>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center">
                <img src="${product.imageUrl}" alt="${product.name}" class="img-fluid product-image" />
            </div>
            <div class="col-md-6">
                <h2>${product.name}</h2>
                <p>${product.description}</p>
                <p><strong>Price:</strong> $${product.price}</p>
                <form action="${pageContext.request.contextPath}/AddToCartServlet" method="post">
                    <input type="hidden" name="productId" value="${product.id}" />
                    <div class="form-group">
                        <label for="quantity">Quantity:</label>
                        <input type="number" name="quantity" id="quantity" class="form-control" value="1" min="1" />
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Add to Cart</button>
                </form>
            </div>
        </div>
    </div>
    <footer>
        &copy; 2024 Toy Store. All rights reserved.
    </footer>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
