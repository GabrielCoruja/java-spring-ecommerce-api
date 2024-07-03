package com.gabriel.ecommerce.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Product sale id.
 */
public class ProductSaleId implements Serializable {

  private Long product;
  private Long sale;

  /**
   * Instantiates a new Product sale id.
   */
  public ProductSaleId() {
  }

  /**
   * Instantiates a new Product sale id.
   *
   * @param product the product
   * @param sale    the sale
   */
  public ProductSaleId(Long product, Long sale) {
    this.product = product;
    this.sale = sale;
  }

  /**
   * Gets product.
   *
   * @return the product
   */
  public Long getProduct() {
    return product;
  }

  /**
   * Sets product.
   *
   * @param product the product
   */
  public void setProduct(Long product) {
    this.product = product;
  }

  /**
   * Gets sale.
   *
   * @return the sale
   */
  public Long getSale() {
    return sale;
  }

  /**
   * Sets sale.
   *
   * @param sale the sale
   */
  public void setSale(Long sale) {
    this.sale = sale;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductSaleId that = (ProductSaleId) o;
    return Objects.equals(product, that.product)
        && Objects.equals(sale, that.sale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, sale);
  }
}
