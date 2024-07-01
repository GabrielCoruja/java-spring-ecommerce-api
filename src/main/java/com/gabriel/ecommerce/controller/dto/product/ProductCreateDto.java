package com.gabriel.ecommerce.controller.dto.product;

import com.gabriel.ecommerce.entity.Product;

public record ProductCreateDto(String name, double price) {

  public Product toEntity() {
    return new Product(
        name,
        price
    );
  }
}
