package com.gabriel.ecommerce.controller.dto.product;

import com.gabriel.ecommerce.entity.Product;

/**
 * The type Product create dto.
 */
public record ProductCreateDto(String name, double price) {

  /**
   * To entity product.
   *
   * @return the product
   */
  public Product toEntity() {
    return new Product(
        name,
        price
    );
  }
}
