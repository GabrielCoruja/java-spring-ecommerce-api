package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.controller.dto.product.ProductCreateDto;
import com.gabriel.ecommerce.controller.dto.product.ProductDto;
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
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    List<Product> products = productService.getAll();

    List<ProductDto> productDtos = products.stream().map(ProductDto::fromEntity).toList();

    return ResponseEntity.status(HttpStatus.OK).body(productDtos);
  }

  /**
   * Gets product.
   *
   * @param productId the product id
   * @return the product
   */
  @GetMapping("/{productId}")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
    Product findProduct = productService.getProduct(productId);

    return ResponseEntity.status(HttpStatus.OK).body(ProductDto.fromEntity(findProduct));
  }

  /**
   * Create product response entity.
   *
   * @param productCreateDto the product create dto
   * @return the response entity
   */
  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreateDto productCreateDto) {
    Product newProduct = productService.create(productCreateDto.toEntity());

    return ResponseEntity.status(HttpStatus.CREATED).body(ProductDto.fromEntity(newProduct));
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
  public ResponseEntity<ProductDto> updateProduct(
      @RequestBody ProductCreateDto productCreateDto,
      @PathVariable Long productId
  ) {
    Product updateProduct = productService.update(productCreateDto.toEntity(), productId);

    return ResponseEntity.status(HttpStatus.OK).body(ProductDto.fromEntity(updateProduct));
  }

  /**
   * Remove product response entity.
   *
   * @param productId the product id
   * @return the response entity
   */
  @DeleteMapping("/{productId}")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<ProductDto> removeProduct(@PathVariable Long productId) {
    productService.remove(productId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
