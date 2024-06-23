package com.gabriel.ecommerce.controller;

import com.gabriel.ecommerce.controller.dto.TokenDto;
import com.gabriel.ecommerce.controller.dto.user.LoginDto;
import com.gabriel.ecommerce.controller.dto.user.UserCreateDto;
import com.gabriel.ecommerce.controller.dto.user.UserDto;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.service.TokenService;
import com.gabriel.ecommerce.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of person.
 */
@RestController
@RequestMapping(value = "/")
public class UserController {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  /**
   * Constructor Person Controller.
   */
  @Autowired
  public UserController(
      UserService userService,
      AuthenticationManager authenticationManager,
      TokenService tokenService
  ) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Register a person.
   */
  @PostMapping("/users")
  public ResponseEntity<UserDto> createUser(
      @RequestBody UserCreateDto userCreateDto
  ) {
    User user = userService.create(userCreateDto.toEntity());

    return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.fromEntity(user));
  }

  /**
   * Login.
   */
  @PostMapping("/auth/login")
  public ResponseEntity<TokenDto> login(
      @RequestBody LoginDto loginDto
  ) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);

    String token = tokenService.generateToken(auth.getName());

    return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
  }

  /**
   * Return all people.
   */
  @GetMapping("/users")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<UserDto>> getAll() {
    List<User> users = userService.getAll();

    List<UserDto> usersDto = users.stream().map(UserDto::fromEntity).toList();

    return ResponseEntity.status(HttpStatus.OK).body(usersDto);
  }
}
