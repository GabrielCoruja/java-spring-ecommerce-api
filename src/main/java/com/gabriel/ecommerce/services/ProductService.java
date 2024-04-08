package com.gabriel.ecommerce.services;

import com.gabriel.ecommerce.dto.user.UserCreateDto;
import com.gabriel.ecommerce.dto.user.UserDto;
import com.gabriel.ecommerce.models.entities.Product;
import com.gabriel.ecommerce.models.entities.User;
import com.gabriel.ecommerce.models.repositories.ProductRepository;
import com.gabriel.ecommerce.models.repositories.UserRepository;
import com.gabriel.ecommerce.utils.UserDtoConvert;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
