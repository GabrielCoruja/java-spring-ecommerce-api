package com.gabriel.ecommerce.controllers;

import com.gabriel.ecommerce.models.entities.Product;
import com.gabriel.ecommerce.models.entities.Sale;
import com.gabriel.ecommerce.services.ProductService;
import com.gabriel.ecommerce.services.SaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of sale.
 */
@RestController
@RequestMapping(value = "/sales")
public class SaleController {

  private final SaleService saleService;

  /**
   * Constructor Sale Controller.
   */
  @Autowired
  public SaleController(SaleService saleService) {
    this.saleService = saleService;
  }

  /**
   * Return all sales.
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<List<Sale>> getAllSales() {
    List<Sale> products = saleService.getAll();

    return ResponseEntity.status(HttpStatus.OK).body(products);
  }

}
