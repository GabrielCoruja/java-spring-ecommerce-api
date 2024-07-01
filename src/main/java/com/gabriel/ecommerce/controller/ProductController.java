package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.controller.dto.product.ProductCreateDto;
import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
   *
   * @param productService the product service
   */
  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Return all products.
   *
   * @return the all products
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<List<Product>> getAllProducts() {
    List<Product> products = productService.getAll();

    return ResponseEntity.status(HttpStatus.OK).body(products);
  }

  /**
   * Create product response entity.
   *
   * @param productCreateDto the product create dto
   * @return the response entity
   */
  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDto productCreateDto) {
    Product newProduct = productService.create(productCreateDto.toEntity());

    return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
  }

  /**
   * Update product response entity.
   *
   * @param productCreateDto the product create dto
   * @param productId        the product id
   * @return the response entity
   */
  @PutMapping("/{productId}")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<Product> updateProduct(@RequestBody ProductCreateDto productCreateDto,
      @PathVariable Long productId) {
    Product updateProduct = productService.update(productCreateDto.toEntity(), productId);

    return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
  }

  /**
   * Remove product response entity.
   *
   * @param productId the product id
   * @return the response entity
   */
  @DeleteMapping("/{productId}")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<Product> removeProduct(@PathVariable Long productId) {
    productService.remove(productId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
