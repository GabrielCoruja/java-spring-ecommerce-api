package com.gabriel.ecommerce.controller.dto.sale;

import com.gabriel.ecommerce.entity.Sale;
import java.util.List;

/**
 * The type Sale products dto.
 */
public record SaleProductsDto(Long saleId, List<ProductSaleDto> products) {

  /**
   * From entity sale products dto.
   *
   * @param sale the sale
   * @return the sale products dto
   */
  public static SaleProductsDto fromEntity(Sale sale) {
    return new SaleProductsDto(
        sale.getId(),
        sale.getProducts().stream()
            .map((productSale) -> new ProductSaleDto(productSale.getSale().getId(),
                productSale.getQuantity())).toList()
    );
  }
}
