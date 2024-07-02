package com.gabriel.ecommerce.controller.dto.user;

import com.gabriel.ecommerce.entity.Sale;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.security.Role;
import java.util.List;

/**
 * The type User sales dto.
 */
public record UserSalesDto(Long id, String username, Role role, List<Sale> sales) {

  /**
   * From entity user sales dto.
   *
   * @param user the user
   * @return the user sales dto
   */
  public static UserSalesDto fromEntity(User user) {
    return new UserSalesDto(
        user.getId(),
        user.getUsername(),
        user.getRole(),
        user.getSales()
    );
  }
}
