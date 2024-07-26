<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Product List - Toy Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Your existing CSS */
    </style>
    <script>
        function submitForm(event) {
            event.preventDefault(); // Prevent the default form submission
            var form = document.getElementById('productForm');
            var formData = new FormData(form);

            fetch(form.action, {
                method: form.method,
                body: formData
            })
            .then(response => response.text())
            .then(result => {
                // Assuming the response indicates a successful addition
                window.location.href = 'Home.jsp';
            })
            .catch(error => console.error('Error:', error));
        }
    </script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="Home.jsp">Home</a>
        <a class="navbar-brand" href="product_list.jsp">Products</a>
        <a class="navbar-brand" href="cart.jsp">Cart</a>
    </nav>
    <header class="text-center mb-4">
        <h1>Our Exciting Toy Collection</h1>
    </header>
    <div class="container">
        <!-- Add Product Form -->
        <form id="productForm" action="products" method="post" enctype="multipart/form-data" onsubmit="submitForm(event)">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
                <label for="name">Product Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="stock">Stock</label>
                <input type="number" class="form-control" id="stock" name="stock" required>
            </div>
            <div class="form-group">
                <label for="image">Product Image</label>
                <input type="file" class="form-control-file" id="image" name="image" required>
            </div>
            <div class="form-group">
                <label for="category">Category</label>
                <select class="form-control" id="category" name="category" required>
                    <!-- Assume categories are dynamically populated -->
                    <option value="1">Kids Toys for Boys</option>
                    <option value="2">Children's Toys for Girls</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Product</button>
        </form>
        <br>
        <ul class="row product-list">
            <c:forEach var="product" items="${products}">
                <li class="col-lg-4 col-md-6 col-sm-12 product-item">
                    <img src="${product.imageUrl}" alt="${product.name}" class="img-fluid">
                    <h2><a href="product_detail.jsp?id=${product.id}">${product.name}</a></h2>
                    <p>${product.description}</p>
                    <p><strong>Price:</strong> $${product.price}</p>
                    <p><strong>Stock:</strong> ${product.stock}</p>
                    <form action="products" method="post" style="display: inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${product.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                    <form action="update_product.jsp" method="get" style="display: inline;">
                        <input type="hidden" name="id" value="${product.id}">
                        <button type="submit" class="btn btn-secondary">Edit</button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
    <footer>
        &copy; 2024 Toy Store. All rights reserved.
    </footer>
    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
