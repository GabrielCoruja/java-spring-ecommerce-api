package com.gabriel.ecommerce.models.repositories;

import com.gabriel.ecommerce.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Repository to operations of Users.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
