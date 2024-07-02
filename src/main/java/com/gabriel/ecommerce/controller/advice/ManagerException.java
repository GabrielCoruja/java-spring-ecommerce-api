package com.gabriel.ecommerce.controller.advice;

import com.gabriel.ecommerce.controller.dto.ErrorMessageDto;
import com.gabriel.ecommerce.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class for exception handling.
 */
@RestControllerAdvice
public class ManagerException {

  /**
   * Not found exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({
      NotFoundException.class,
  })
  public ResponseEntity<ErrorMessageDto> notFoundException(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessageDto(exception.getMessage()));
  }

  /**
   * Handle data integrity violation exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({
      DataIntegrityViolationException.class
  })
  public ResponseEntity<ErrorMessageDto> handleDataIntegrityViolationException(
      RuntimeException exception
  ) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorMessageDto("Nome de usuário ou e-mail já cadastrado!"));
  }

  /**
   * Internal authentication service exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler({
      InternalAuthenticationServiceException.class,
      BadCredentialsException.class,
      AccessDeniedException.class
  })
  public ResponseEntity<ErrorMessageDto> internalAuthenticationServiceException(
      RuntimeException exception
  ) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(new ErrorMessageDto("Credenciais inválidas!"));
  }

  /**
   * Server error exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler
  public ResponseEntity<ErrorMessageDto> serverErrorException(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorMessageDto(exception.getMessage()));
  }
}
