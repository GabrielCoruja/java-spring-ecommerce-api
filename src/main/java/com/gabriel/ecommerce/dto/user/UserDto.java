package com.gabriel.ecommerce.dto.user;

import com.gabriel.ecommerce.security.Role;

/**
 * Record DTO to create a new person.
 */
public record UserDto(Long id, String username, Role role) {

}
