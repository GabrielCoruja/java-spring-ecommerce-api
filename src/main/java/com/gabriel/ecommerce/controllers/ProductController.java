package com.gabriel.ecommerce.controllers;

import com.gabriel.ecommerce.dto.user.LoginDto;
import com.gabriel.ecommerce.dto.user.UserCreateDto;
import com.gabriel.ecommerce.dto.user.UserDto;
import com.gabriel.ecommerce.models.entities.Product;
import com.gabriel.ecommerce.models.entities.User;
import com.gabriel.ecommerce.services.ProductService;
import com.gabriel.ecommerce.services.TokenService;
import com.gabriel.ecommerce.services.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
