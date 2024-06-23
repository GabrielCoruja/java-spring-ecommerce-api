package com.gabriel.ecommerce.controller.dto.user;

import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.security.Role;

/**
 * Record DTO to create a new person.
 */
public record UserDto(Long id, String username, Role role) {

  /**
   * Convert from UserEnttty to UserDto.
   */
  public static UserDto fromEntity(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        user.getRole()
    );
  }

}
