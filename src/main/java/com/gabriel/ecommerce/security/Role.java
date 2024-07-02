package com.gabriel.ecommerce.security;

/**
 * Enum representing a Role.
 */
public enum Role {

  ADMIN("ROLE_ADMIN"),

  MANAGER("ROLE_MANAGER"),

  USER("ROLE_USER");

  private final String name;

  Role(String name) {
    this.name = name;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }
}
