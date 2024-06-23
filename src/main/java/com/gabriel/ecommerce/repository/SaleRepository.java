package com.gabriel.ecommerce.repository;

import com.gabriel.ecommerce.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to operations of Users.
 */
@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
