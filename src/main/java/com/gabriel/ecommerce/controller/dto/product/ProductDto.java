package com.gabriel.ecommerce.controller.dto.product;

import com.gabriel.ecommerce.entity.Product;

/**
 * The type Product dto.
 */
public record ProductDto(Long id, String name, double price) {

  /**
   * From entity product dto.
   *
   * @param product the product
   * @return the product dto
   */
  public static ProductDto fromEntity(Product product) {
    return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice()
    );
  }

}
