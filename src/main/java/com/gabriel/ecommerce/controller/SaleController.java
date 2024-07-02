package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.controller.dto.sale.SaleCreateDto;
import com.gabriel.ecommerce.controller.dto.sale.SaleProductsDto;
import com.gabriel.ecommerce.entity.Sale;
import com.gabriel.ecommerce.service.SaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
   *
   * @param saleService the sale service
   */
  @Autowired
  public SaleController(SaleService saleService) {
    this.saleService = saleService;
  }

  /**
   * Return all sales.
   *
   * @return the all sales
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<List<SaleProductsDto>> getAllSales() {
    List<Sale> products = saleService.getAll();

    List<SaleProductsDto> sales = products.stream().map(SaleProductsDto::fromEntity).toList();

    return ResponseEntity.status(HttpStatus.OK).body(sales);
  }

  /**
   * Gets product.
   *
   * @param saleId the sale id
   * @return the product
   */
  @GetMapping("/{saleId}")
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<SaleProductsDto> getProduct(@PathVariable Long saleId) {
    Sale findsale = saleService.getSale(saleId);

    return ResponseEntity.status(HttpStatus.OK).body(SaleProductsDto.fromEntity(findsale));
  }

  @PostMapping
//  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
  public ResponseEntity<Sale> createSale(@RequestBody List<SaleCreateDto> saleCreateDto) {
    Sale newSale = saleService.create(saleCreateDto);

//    SaleProductsDto sale = new SaleProductsDto(newSale.getId(), newSale.getProducts());

    return ResponseEntity.status(HttpStatus.OK).body(newSale);
  }

}
