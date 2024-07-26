package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Product;
import repositories.ProductRepository;
import services.ProductService;

@WebServlet("/products")
@MultipartConfig
public class ProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        this.productService = new ProductService(new ProductRepository((Connection) getServletContext().getAttribute("DBConnection")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addProduct(req, resp);
                break;
            case "update":
                updateProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            case "uploadImage":
                uploadImage(req, resp);
                break;
        }
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int stock = Integer.parseInt(req.getParameter("stock"));
        Part filePart = req.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + "uploads";
        filePart.write(uploadPath + "/" + fileName);
        Product product = new Product(0, name, price, description, stock, "uploads/" + fileName);
        productService.addProduct(product);
        // Redirect to home.jsp after adding product
        resp.sendRedirect("products");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        int stock = Integer.parseInt(req.getParameter("stock"));
        Product product = new Product(id, name, price, description, stock, null);
        productService.updateProduct(product);
        resp.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProduct(id);
        resp.sendRedirect("products");
    }

    private void uploadImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        Part filePart = req.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + "uploads";
        filePart.write(uploadPath + "/" + fileName);
        productService.updateProductImage(productId, "uploads/" + fileName);
        resp.sendRedirect("products");
    }
}
