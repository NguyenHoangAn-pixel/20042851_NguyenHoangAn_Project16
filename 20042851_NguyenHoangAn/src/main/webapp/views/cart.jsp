<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cart and Categories - Toy Store</title>
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
            margin-bottom: 60px;
        }
        header {
            background-color: #FF5733;
            color: white;
            padding: 10px 0;
            text-align: center;
            margin-bottom: 20px;
            border-radius: 10px;
        }
        nav {
            background-color: #FF5733;
            color: white;
            padding: 10px 0;
            text-align: center;
            border-radius: 10px;
        }
        nav a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
        }
        .content {
            padding: 20px;
        }
        .table {
            margin-bottom: 40px;
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
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="Home.jsp">Home</a>
    </nav>
    <div class="container">
        <header>
            <h1>Category List and Cart</h1>
            <p>Review categories and your selected toys before proceeding to checkout</p>
        </header>
        
        <c:choose>
            <c:when test="${not empty category}">
                <div class="content">
                    <h2>${category.categoryName}</h2>
                    <p>${category.description}</p>
                </div>
                <div class="content">
                    <a href="${pageContext.request.contextPath}/categories" class="btn btn-primary">Back to Category List</a>
                </div>
            </c:when>
            <c:otherwise>
                <header>
                    <h1>Category List</h1>
                </header>
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td>${category.categoryID}</td>
                                <td>${category.categoryName}</td>
                                <td>${category.description}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/categories/${category.categoryID}" class="btn btn-info">View Details</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <header>
            <h1>Your Cart</h1>
            <p>Review your selected toys before proceeding to checkout</p>
        </header>

        <c:choose>
            <c:when test="${not empty cart.items}">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart.items}" var="item">
                            <tr>
                                <td>${item.product.name}</td>
                                <td>${item.quantity}</td>
                                <td>${item.product.price}</td>
                                <td>${item.product.price * item.quantity}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="content">
                    <form action="${pageContext.request.contextPath}/checkout" method="post">
                        <button type="submit" class="btn btn-success btn-lg">Proceed to Checkout</button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="content">
                    <p>Your cart is empty. Please add some items to your cart before proceeding.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <footer>
        &copy; 2024 Toy Store. All rights reserved.
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
