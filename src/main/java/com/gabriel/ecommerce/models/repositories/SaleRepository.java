package com.gabriel.ecommerce.models.repositories;

import com.gabriel.ecommerce.models.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to operations of Users.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
