package com.gabriel.ecommerce.repository;

import com.gabriel.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to operations of Users.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
