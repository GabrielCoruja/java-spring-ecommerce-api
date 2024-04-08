package com.gabriel.ecommerce.models.repositories;

import com.gabriel.ecommerce.models.entities.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to operations of Users.
 */
@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
}
