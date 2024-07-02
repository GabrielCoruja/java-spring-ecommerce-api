package com.gabriel.ecommerce.controller.dto.user;

import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.security.Role;

/**
 * DTO to return data User.
 */
public record UserCreateDto(String username, String password, String email, Role role) {

  /**
   * Convert from creation UserDto to entity User.
   *
   * @return the user
   */
  public User toEntity() {
    User user = new User();

    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);
    user.setRole(role);

    return user;
  }

}
