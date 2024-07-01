package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service interactive product data.
 */
@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Return all products.
   */
  public List<Product> getAll() {
    return productRepository.findAll();
  }

  /**
   * Find a product.
   */
  public Product getProduct(Long id) {
    return productRepository
        .findById(id).orElseThrow(() -> new NotFoundException("Product Not Found!"));
  }

  /**
   * Create a new product.
   */
  public Product create(Product product) {
    return productRepository.save(product);
  }

  /**
   * Update a product.
   */
  public Product update(Product product, Long productId) {
    Product findProduct = getProduct(productId);

    findProduct.setName(product.getName());
    findProduct.setPrice(product.getPrice());

    return productRepository.save(findProduct);
  }

  /**
   * Delete a product.
   */
  public void remove(Long productId) {
    Product findProduct = getProduct(productId);

    productRepository.delete(findProduct);
  }
}
