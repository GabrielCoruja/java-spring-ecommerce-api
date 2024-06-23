package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of product.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private final ProductService productService;

  /**
   * Constructor Product Controller.
   */
  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Return all products.
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAll();

    return ResponseEntity.status(HttpStatus.OK).body(products);
  }

}
