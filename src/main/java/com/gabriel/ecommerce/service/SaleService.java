package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.entity.Sale;
import com.gabriel.ecommerce.repository.SaleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Sale service.
 */
@Service
public class SaleService {

  private final SaleRepository saleRepository;

  /**
   * Instantiates a new Sale service.
   *
   * @param saleRepository the sale repository.
   */
  @Autowired
  public SaleService(SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  /**
   * Gets all.
   *
   * @return the all sales.
   */
  public List<Sale> getAll() {
    return saleRepository.findAll();
  }
}
