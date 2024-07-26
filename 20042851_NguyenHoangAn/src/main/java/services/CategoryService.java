package services;

import java.sql.SQLException;
import java.util.List;

import models.Category;
import models.Product;
import repositories.CategoryRepository;
import repositories.ProductRepository;

public class CategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public void createCategory(Category category) throws SQLException {
        categoryRepository.save(category);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryRepository.update(category);
    }

    public void deleteCategory(int id) throws SQLException {
        categoryRepository.delete(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    public void createProduct(Product product) throws SQLException {
        productRepository.save(product);
    }

    public void updateProduct(Product product) throws SQLException {
        productRepository.update(product);
    }

    public void deleteProduct(int id) throws SQLException {
        productRepository.delete(id);
    }
}
