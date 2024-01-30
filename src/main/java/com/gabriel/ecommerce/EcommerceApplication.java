package com.gabriel.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application main class.
 */
@SpringBootApplication
@EntityScan({"com.gabriel.ecommerce.models.entities"})
@EnableJpaRepositories(basePackages = { "com.gabriel.ecommerce.models.repositories" })
@ComponentScan("com.gabriel.ecommerce")
public class EcommerceApplication {
  public static  void main(String[] args) {
    SpringApplication.run(EcommerceApplication.class, args);
  }
}
