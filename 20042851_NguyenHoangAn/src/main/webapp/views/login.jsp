<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Toy Store</title>
    <style>
        body {
            font-family: 'Comic Sans MS', 'Arial', sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-image: url('<%= request.getContextPath() %>/upload/image/background.jpg');
            background-size: cover;
        }
        .login-container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            width: 320px;
            text-align: center;
        }
        .login-container h1 {
            margin-bottom: 20px;
            color: #333;
            font-size: 24px;
        }
        .login-container input[type="email"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        .login-container input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #FF5733;
            border: none;
            border-radius: 10px;
            color: white;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .login-container input[type="submit"]:hover {
            background-color: #C70039;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
        .login-container .footer {
            margin-top: 20px;
            font-size: 14px;
        }
        .login-container .footer a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1>Welcome Back!</h1>
        <form action="users?action=login" method="post">
            <input type="email" name="email" placeholder="Email" required /><br />
            <input type="password" name="password" placeholder="Password" required /><br />
            <input type="submit" value="Login" />
        </form>
        <p class="error-message">${errorMessage}</p>
        <div class="footer">
            <p>New to Toy Store? <a href="register.jsp">Create an account</a></p>
            <p><a href="forgot_password.jsp">Forgot your password?</a></p>
        </div>
    </div>
</body>
</html>
