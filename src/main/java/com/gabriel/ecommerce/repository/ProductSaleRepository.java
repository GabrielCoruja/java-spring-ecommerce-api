package com.gabriel.ecommerce.repository;

import com.gabriel.ecommerce.entity.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to operations of Users.
 */
@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {

}
