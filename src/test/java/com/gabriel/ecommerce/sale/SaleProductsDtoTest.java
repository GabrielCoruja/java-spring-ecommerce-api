package com.gabriel.ecommerce.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gabriel.ecommerce.controller.dto.sale.ProductSaleDto;
import com.gabriel.ecommerce.controller.dto.sale.SaleProductsDto;
import com.gabriel.ecommerce.entity.Product;
import com.gabriel.ecommerce.entity.ProductSale;
import com.gabriel.ecommerce.entity.Sale;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SaleProductsDtoTest {

  @Test
  @DisplayName("Converte a venda usando o id de cada produto, não o id da venda")
  void fromEntityUsesProductIds() {
    Product rice = new Product("Arroz", 10.0);
    rice.setId(1L);
    Product beans = new Product("Feijao", 20.0);
    beans.setId(2L);

    Sale sale = new Sale();
    sale.setId(7L);
    sale.setProducts(List.of(
        new ProductSale(rice, sale, 20),
        new ProductSale(beans, sale, 10)
    ));

    SaleProductsDto dto = SaleProductsDto.fromEntity(sale);

    assertEquals(7L, dto.saleId());
    assertEquals(
        List.of(new ProductSaleDto(1L, 20), new ProductSaleDto(2L, 10)),
        dto.products()
    );
  }
}
