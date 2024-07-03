package com.gabriel.ecommerce.seed;

import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.entity.ProductSale;
import com.gabriel.ecommerce.entity.Sale;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.repository.ProductRepository;
import com.gabriel.ecommerce.repository.ProductSaleRepository;
import com.gabriel.ecommerce.repository.SaleRepository;
import com.gabriel.ecommerce.repository.UserRepository;
import com.gabriel.ecommerce.security.Role;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * The type Database seeder.
 */
@Profile("!test")

@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final ProductRepository productRepository;
  private final SaleRepository saleRepository;
  private final ProductSaleRepository productSaleRepository;
  private final UserRepository userRepository;

  private List<Product> products = new ArrayList<>();
  private List<Sale> sales = new ArrayList<>();

  /**
   * Instantiates a new Database seeder.
   *
   * @param productRepository     the product repository
   * @param saleRepository        the sale repository
   * @param productSaleRepository the product sale repository
   * @param userRepository        the user repository
   */
  @Autowired
  public DatabaseSeeder(
      ProductRepository productRepository,
      SaleRepository saleRepository,
      ProductSaleRepository productSaleRepository,
      UserRepository userRepository
  ) {
    this.productRepository = productRepository;
    this.saleRepository = saleRepository;
    this.productSaleRepository = productSaleRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    seedProducts();
    seedSales();
    seedProductsSales();
    seedUsers();
  }

  private void seedProducts() {
    products.add(new Product("Arroz", (double) 10));
    products.add(new Product("Feijao", (double) 20));

    products = productRepository.saveAll(products);
  }

  private void seedSales() {
    sales.add(new Sale());
    sales.add(new Sale());
    sales.add(new Sale());

    sales = saleRepository.saveAll(sales);
  }

  private void seedUsers() {
    String hashPassword = new BCryptPasswordEncoder().encode("password");
    User user = new User(null, "coruja", "coruja@email.com", hashPassword, Role.ADMIN);

    userRepository.save(user);
  }

  private void seedProductsSales() {
    List<ProductSale> productSales = new ArrayList<>();

    productSales.add(new ProductSale(products.get(0), sales.get(0), 20));
    productSales.add(new ProductSale(products.get(1), sales.get(0), 10));

    productSaleRepository.saveAll(productSales);
  }
}