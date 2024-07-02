package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.controller.dto.sale.SaleCreateDto;
import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.entity.ProductSale;
import com.gabriel.ecommerce.entity.Sale;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.repository.ProductSaleRepository;
import com.gabriel.ecommerce.repository.SaleRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Sale service.
 */
@Service
public class SaleService {

  private final SaleRepository saleRepository;
  private final ProductSaleRepository productSaleRepository;
  private final ProductService productService;

  /**
   * Instantiates a new Sale service.
   *
   * @param saleRepository        the sale repository.
   * @param productSaleRepository the product sale repository
   * @param productService        the product service
   */
  @Autowired
  public SaleService(
      SaleRepository saleRepository,
      ProductSaleRepository productSaleRepository,
      ProductService productService
  ) {
    this.saleRepository = saleRepository;
    this.productSaleRepository = productSaleRepository;
    this.productService = productService;
  }

  /**
   * Gets all.
   *
   * @return the all sales.
   */
  public List<Sale> getAll() {
    return saleRepository.findAll();
  }

  /**
   * Gets sale.
   *
   * @param saleId the sale id
   * @return the sale
   */
  public Sale getSale(Long saleId) {
    System.out.println("Tesssssaadasdad");
    return saleRepository
        .findById(saleId).orElseThrow(() -> new NotFoundException("Sale Not Found!"));
  }

  /**
   * Create list.
   *
   * @param products the products
   * @return the list
   */
  @Transactional
  public Sale create(List<SaleCreateDto> products) {
    Sale sale = new Sale();
    Sale saveSale = saleRepository.save(sale);

    List<ProductSale> productSales = products.stream()
        .map(product -> {
          Product findProduct = productService.getProduct(product.productId());
          return new ProductSale(findProduct, saveSale, product.quantity());
        })
        .toList();

    productSaleRepository.saveAll(productSales);
    saveSale.getProducts().addAll(productSales);

    return saveSale;
  }

}
