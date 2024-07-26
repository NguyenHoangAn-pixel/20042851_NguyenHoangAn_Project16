package services;

import java.util.List;
import models.Product;
import repositories.ProductRepository;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public void updateProductImage(int productId, String imagePath) {
        productRepository.updateProductImage(productId, imagePath);
    }
}
