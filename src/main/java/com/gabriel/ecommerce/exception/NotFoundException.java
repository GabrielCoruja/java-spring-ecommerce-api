package com.gabriel.ecommerce.exception;

/**
 * Exception error not found.
 */
public class NotFoundException extends RuntimeException {

  /**
   * Instantiates a new Not found exception.
   *
   * @param message the message
   */
  public NotFoundException(String message) {
    super(message);
  }
}
