package com.gabriel.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The type Product sale.
 */
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

  /**
   * Instantiates a new Product sale.
   */
  public ProductSale() {
  }

  /**
   * Instantiates a new Product sale.
   *
   * @param product  the product
   * @param sale     the sale
   * @param quantity the quantity
   */
  public ProductSale(Product product, Sale sale, int quantity) {
    this.product = product;
    this.sale = sale;
    this.quantity = quantity;
  }

  /**
   * Gets product.
   *
   * @return the product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Sets product.
   *
   * @param product the product
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * Gets sale.
   *
   * @return the sale
   */
  public Sale getSale() {
    return sale;
  }

  /**
   * Sets sale.
   *
   * @param sale the sale
   */
  public void setSale(Sale sale) {
    this.sale = sale;
  }

  /**
   * Gets quantity.
   *
   * @return the quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets quantity.
   *
   * @param quantity the quantity
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
