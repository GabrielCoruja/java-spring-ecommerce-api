package com.gabriel.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@IdClass(ProductSaleId.class)
@Table(name = "product_sale")
public class ProductSale {

  @Id
  @ManyToOne
  @JoinColumn(name = "product_id")
  @JsonIgnore
  private Product product;

  @Id
  @ManyToOne
  @JoinColumn(name = "sale_id")
  @JsonIgnore
  private Sale sale;

  private int quantity;

  public ProductSale() {
  }

  public ProductSale(Product product, Sale sale, int quantity) {
    this.product = product;
    this.sale = sale;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Sale getSale() {
    return sale;
  }

  public void setSale(Sale sale) {
    this.sale = sale;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
