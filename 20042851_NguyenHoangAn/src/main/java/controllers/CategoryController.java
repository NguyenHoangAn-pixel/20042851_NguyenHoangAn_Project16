package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Category;
import models.Product;
import repositories.CategoryRepository;
import repositories.ProductRepository;
import services.CategoryService;

@WebServlet("/categories/*")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        this.categoryService = new CategoryService(new CategoryRepository(connection), new ProductRepository(connection));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // List all categories
            List<Category> categories = null;
			try {
				categories = categoryService.getAllCategories();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/category_list.jsp").forward(req, resp);
        } else if (pathInfo.equals("/products")) {
            // List all products
            List<Product> products = categoryService.getAllProducts();
            req.setAttribute("products", products);
            req.getRequestDispatcher("/views/product_list.jsp").forward(req, resp);
        } else {
            // View details of a single category or product
            try {
                if (pathInfo.startsWith("/product/")) {
                    int productId = Integer.parseInt(pathInfo.substring(9));
                    Product product = categoryService.getProductById(productId);
                    if (product == null) {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    } else {
                        req.setAttribute("product", product);
                        req.getRequestDispatcher("/views/product_detail.jsp").forward(req, resp);
                    }
                } else {
                    int categoryId = Integer.parseInt(pathInfo.substring(1));
                    Category category = categoryService.getCategoryById(categoryId);
                    if (category == null) {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    } else {
                        req.setAttribute("category", category);
                        req.getRequestDispatcher("/views/category_detail.jsp").forward(req, resp);
                    }
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            switch (action) {
                case "createCategory":
                    createCategory(req, resp);
                    break;
                case "updateCategory":
                    updateCategory(req, resp);
                    break;
                case "deleteCategory":
                    deleteCategory(req, resp);
                    break;
                case "createProduct":
                    createProduct(req, resp);
                    break;
                case "updateProduct":
                    updateProduct(req, resp);
                    break;
                case "deleteProduct":
                    deleteProduct(req, resp);
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void createCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if (name == null || name.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Category category = new Category();
        category.setCategoryName(name);
        category.setDescription(description);
        categoryService.createCategory(category);

        resp.sendRedirect(req.getContextPath() + "/categories");
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if (id == null || name == null || name.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int categoryId = Integer.parseInt(id);
        Category category = new Category();
        category.setCategoryID(categoryId);
        category.setCategoryName(name);
        category.setDescription(description);
        categoryService.updateCategory(category);

        resp.sendRedirect(req.getContextPath() + "/categories");
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String id = req.getParameter("id");

        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int categoryId = Integer.parseInt(id);
        categoryService.deleteCategory(categoryId);

        resp.sendRedirect(req.getContextPath() + "/categories");
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String name = req.getParameter("name");
        String categoryId = req.getParameter("categoryId");
        String price = req.getParameter("price");
        String description = req.getParameter("description");

        if (name == null || name.isEmpty() || categoryId == null || price == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Product product = new Product(0, description, 0, description, 0, description);
        product.setProductName(name);
        product.setCategoryID(Integer.parseInt(categoryId));
        product.setPrice(Double.parseDouble(price));
        product.setDescription(description);
        categoryService.createProduct(product);

        resp.sendRedirect(req.getContextPath() + "/categories/products");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String categoryId = req.getParameter("categoryId");
        String price = req.getParameter("price");
        String description = req.getParameter("description");

        if (id == null || name == null || name.isEmpty() || categoryId == null || price == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int productId = Integer.parseInt(id);
        Product product = new Product(productId, description, productId, description, productId, description);
        product.setProductID(productId);
        product.setProductName(name);
        product.setCategoryID(Integer.parseInt(categoryId));
        product.setPrice(Double.parseDouble(price));
        product.setDescription(description);
        categoryService.updateProduct(product);

        resp.sendRedirect(req.getContextPath() + "/categories/products");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String id = req.getParameter("id");

        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int productId = Integer.parseInt(id);
        categoryService.deleteProduct(productId);

        resp.sendRedirect(req.getContextPath() + "/categories/products");
    }
}
