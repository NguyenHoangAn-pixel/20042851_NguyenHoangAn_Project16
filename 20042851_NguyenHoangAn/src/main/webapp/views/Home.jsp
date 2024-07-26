<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home - Toy Store</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/path/to/styles.css">
    <style>
        body {
            font-family: 'Comic Sans MS', 'Arial', sans-serif;
            background-color: #fefefe;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #FFBF00; /* Bright yellow for fun */
            color: #fff;
            padding: 20px 0;
            text-align: center;
            border-bottom: 5px solid #FF5733; /* Bright orange border */
        }
        nav {
            background-color: #FF5733; /* Bright orange for navigation bar */
            color: white;
            padding: 10px 0;
            text-align: center;
            border-bottom: 3px solid #FFC107; /* Bright yellow border */
        }
        nav a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 1.1rem;
            font-weight: bold;
        }
        nav a:hover {
            text-decoration: underline;
        }
        .container {
            width: 90%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 10px;
        }
        .content {
            padding: 20px;
            text-align: center;
        }
        .content img {
            width: 100%;
            max-width: 800px;
            height: auto;
            border-radius: 10px;
        }
        .toy-grid {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin-top: 20px;
        }
        .toy-item {
            background-color: #f8f9fa;
            margin: 10px;
            padding: 10px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: calc(33.333% - 20px);
            text-align: center;
        }
        .toy-item img {
            max-width: 150px;
            height: auto;
            border-radius: 10px;
        }
        .toy-item p {
            margin: 10px 0 0;
            font-size: 16px;
            color: #333;
            font-weight: bold;
        }
        footer {
            background-color: #FFBF00; /* Matching footer color */
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
    <header>
        <h1>Welcome to Toy Store!</h1>
        <p>Your one-stop shop for the best toys for kids!</p>
    </header>
    <nav>
        <a href="home.jsp">Home</a>
        <a href="product_list.jsp">Products</a>
        <a href="cart.jsp">Cart</a>
        <a href="product_detail.jsp">Detail</a>
        <a href="checkout.jsp">Checkout</a>
        <a href="register.jsp">Register</a>
        <a href="login.jsp">Login</a>
    </nav>
    <div class="container">
        <div class="content">
            <img src="<%= request.getContextPath() %>/upload/image/trangbia.jpg" alt="Welcome Image">
            <h2>Discover the Best Toys for Your Little Ones!</h2>
            <p>Explore our collection of fun and exciting toys that kids love!</p>

            <div class="toy-grid">
                <div class="toy-item">
                    <img src="<%= request.getContextPath() %>/upload/image/toy1.jpg" alt="Toy 1">
                    <p>$10 - Fun Toy 1</p>
                </div>
                <div class="toy-item">
                    <img src="<%= request.getContextPath() %>/upload/image/toy2.jpg" alt="Toy 2">
                    <p>$15 - Fun Toy 2</p>
                </div>
                <div class="toy-item">
                    <img src="<%= request.getContextPath() %>/upload/image/toy3.jpg" alt="Toy 3">
                    <p>$20 - Fun Toy 3</p>
                </div>
                <div class="toy-item">
                    <img src="<%= request.getContextPath() %>/upload/image/toy4.jpg" alt="Toy 4">
                    <p>$25 - Fun Toy 4</p>
                </div>
                <div class="toy-item">
                    <img src="<%= request.getContextPath() %>/upload/image/toy5.jpg" alt="Toy 5">
                    <p>$30 - Fun Toy 5</p>
                </div>
                <div class="toy-item">
                    <img src="<%= request.getContextPath() %>/upload/image/toy6.jpg" alt="Toy 6">
                    <p>$35 - Fun Toy 6</p>
                </div>
            </div>
        </div>
    </div>
    <footer>
        &copy; 2024 Toy Store. All rights reserved.
    </footer>
</body>
</html>
