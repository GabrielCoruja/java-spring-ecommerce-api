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

  /**
   * Instantiates a new Product service.
   *
   * @param productRepository the product repository
   */
  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Return all products.
   *
   * @return the all
   */
  public List<Product> getAll() {
    return productRepository.findAll();
  }

  /**
   * Find a product.
   *
   * @param productId the product id
   * @return the product
   */
  public Product getProduct(Long productId) {
    return productRepository
        .findById(productId).orElseThrow(() -> new NotFoundException("Product Not Found!"));
  }

  /**
   * Create a new product.
   *
   * @param product the product
   * @return the product
   */
  public Product create(Product product) {
    return productRepository.save(product);
  }

  /**
   * Update a product.
   *
   * @param product   the product
   * @param productId the product id
   * @return the product
   */
  public Product update(Product product, Long productId) {
    Product findProduct = getProduct(productId);

    findProduct.setName(product.getName());
    findProduct.setPrice(product.getPrice());

    return productRepository.save(findProduct);
  }

  /**
   * Delete a product.
   *
   * @param productId the product id
   */
  public void remove(Long productId) {
    Product findProduct = getProduct(productId);

    productRepository.delete(findProduct);
  }
}
