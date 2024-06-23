package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.entity.Product;
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
   * Creates a new user.
   */
  public List<Product> getAll() {
    return productRepository.findAll();
  }
}
