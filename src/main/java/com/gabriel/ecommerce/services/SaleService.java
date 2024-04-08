package com.gabriel.ecommerce.services;

import com.gabriel.ecommerce.models.entities.Sale;
import com.gabriel.ecommerce.models.repositories.SaleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service interactive sale data.
 */
@Service
public class SaleService {

  private final SaleRepository saleRepository;

  @Autowired
  public SaleService(SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  /**
   * Creates a new user.
   */
  public List<Sale> getAll() {
    return saleRepository.findAll();
  }
}
