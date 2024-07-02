package com.gabriel.ecommerce.controller.dto.product;

import com.gabriel.ecommerce.entity.Product;

public record ProductDto(Long id, String name, double price) {

  public static ProductDto fromEntity(Product product) {
    return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice()
    );
  }

}
