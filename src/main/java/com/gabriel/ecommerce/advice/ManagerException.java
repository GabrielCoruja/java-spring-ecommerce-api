package com.gabriel.ecommerce.advice;

import com.gabriel.ecommerce.dto.ErrorMessageDto;
import com.gabriel.ecommerce.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class for exception handling.
 */
@ControllerAdvice
public class ManagerException {

  @ExceptionHandler({
      NotFoundException.class,
  })
  public ResponseEntity<ErrorMessageDto> notFoundException(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorMessageDto(exception.getMessage()));
  }

  @ExceptionHandler({
      DataIntegrityViolationException.class
  })
  public ResponseEntity<ErrorMessageDto> handleDataIntegrityViolationException(
      RuntimeException exception
  ) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErrorMessageDto("Nome de usuário ou e-mail já cadastrado!"));
  }

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

  @ExceptionHandler
  public ResponseEntity<ErrorMessageDto> serverErrorException(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorMessageDto(exception.getMessage()));
  }
}
