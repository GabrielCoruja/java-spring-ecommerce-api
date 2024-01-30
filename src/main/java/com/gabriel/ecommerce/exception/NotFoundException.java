package com.gabriel.ecommerce.exception;

/**
 * Exception error not found.
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
