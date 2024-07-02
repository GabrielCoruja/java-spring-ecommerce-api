package com.gabriel.ecommerce.controller.dto.user;

import com.gabriel.ecommerce.entity.Sale;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.security.Role;
import java.util.List;

public record UserSalesDto(Long id, String username, Role role, List<Sale> sales) {

  public static UserSalesDto fromEntity(User user) {
    return new UserSalesDto(
        user.getId(),
        user.getUsername(),
        user.getRole(),
        user.getSales()
    );
  }
}
