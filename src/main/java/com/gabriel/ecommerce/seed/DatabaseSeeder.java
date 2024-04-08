package com.gabriel.ecommerce.seed;

import com.gabriel.ecommerce.models.entities.Product;
import com.gabriel.ecommerce.models.entities.ProductSale;
import com.gabriel.ecommerce.models.entities.Sale;
import com.gabriel.ecommerce.models.repositories.ProductRepository;
import com.gabriel.ecommerce.models.repositories.ProductSaleRepository;
import com.gabriel.ecommerce.models.repositories.SaleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final ProductRepository productRepository;
  private final SaleRepository saleRepository;
  private final ProductSaleRepository productSaleRepository;

  @Autowired
  public DatabaseSeeder(
      ProductRepository productRepository,
      SaleRepository saleRepository,
      ProductSaleRepository productSaleRepository
  ) {
    this.productRepository = productRepository;
    this.saleRepository = saleRepository;
    this.productSaleRepository = productSaleRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    seedProducts();
    seedSales();
    seedProductsSales();
  }

  private void seedProducts() {
    List<Product> products = new ArrayList<>();

    products.add(new Product("Arroz",  (double) 10));
    products.add(new Product("Feijao", (double) 20));

    productRepository.saveAll(products);
  }

  private void seedSales() {
    List<Sale> sales = new ArrayList<>();

    sales.add(new Sale());
    sales.add(new Sale());
    sales.add(new Sale());

    saleRepository.saveAll(sales);
  }

  private void seedProductsSales() {
    List<ProductSale> productSales = new ArrayList<>();

    List<Product> products = productRepository.findAll();
    List<Sale> sales = saleRepository.findAll();

    productSales.add(new ProductSale(products.get(0), sales.get(0), 20));
    productSales.add(new ProductSale(products.get(1), sales.get(0),  10));

    productSaleRepository.saveAll(productSales);
  }
}