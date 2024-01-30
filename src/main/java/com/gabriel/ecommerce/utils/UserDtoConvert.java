package com.gabriel.ecommerce.utils;

import com.gabriel.ecommerce.dto.user.UserCreateDto;
import com.gabriel.ecommerce.dto.user.UserDto;
import com.gabriel.ecommerce.models.entities.User;

/**
 * Utility class to convert between model and dto crop.
 */
public class UserDtoConvert {

  /**
   * Convert from creation dto to model in new farm.
   */
  public static User dtoToModel(UserCreateDto dto, String hashedPassword) {
    User user = new User();

    user.setUsername(dto.username());
    user.setPassword(hashedPassword);
    user.setEmail(dto.email());
    user.setRole(dto.role());

    return user;
  }

  /**
   * Convert from model to dto.
   */
  public static UserDto modelToDto(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername(),
        user.getRole()
    );
  }
}
