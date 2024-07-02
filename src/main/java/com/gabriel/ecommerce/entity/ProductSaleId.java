package com.gabriel.ecommerce.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductSaleId implements Serializable {

  private Long product;
  private Long sale;

  public ProductSaleId() {
  }
  
  public ProductSaleId(Long product, Long sale) {
    this.product = product;
    this.sale = sale;
  }

  public Long getProduct() {
    return product;
  }

  public void setProduct(Long product) {
    this.product = product;
  }

  public Long getSale() {
    return sale;
  }

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
    return Objects.equals(product, that.product) &&
        Objects.equals(sale, that.sale);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, sale);
  }
}
