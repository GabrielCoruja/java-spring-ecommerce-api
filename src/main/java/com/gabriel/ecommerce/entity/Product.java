package com.gabriel.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Product.
 */
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private double price;

  @OneToMany(mappedBy = "product")
  private List<ProductSale> sales = new ArrayList<>();

  /**
   * Instantiates a new Product.
   */
  public Product() {
  }

  /**
   * Instantiates a new Product.
   *
   * @param name  the name
   * @param price the price
   */
  public Product(String name, Double price) {
    this.name = name;
    this.price = price;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets price.
   *
   * @return the price
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Sets price.
   *
   * @param price the price
   */
  public void setPrice(Double price) {
    this.price = price;
  }

  /**
   * Gets sales.
   *
   * @return the sales
   */
  public List<ProductSale> getSales() {
    return sales;
  }

  /**
   * Sets sales.
   *
   * @param sales the sales
   */
  public void setSales(List<ProductSale> sales) {
    this.sales = sales;
  }
}
