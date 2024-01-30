package com.gabriel.ecommerce.dto.user;

import com.gabriel.ecommerce.security.Role;

/**
 * DTO to return data user.
 */
public record UserCreateDto(String username, String password, String email, Role role) {

}
