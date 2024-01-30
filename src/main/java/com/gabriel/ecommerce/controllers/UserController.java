package com.gabriel.ecommerce.controllers;

import com.gabriel.ecommerce.dto.user.LoginDto;
import com.gabriel.ecommerce.dto.user.UserCreateDto;
import com.gabriel.ecommerce.dto.user.UserDto;
import com.gabriel.ecommerce.models.entities.User;
import com.gabriel.ecommerce.services.TokenService;
import com.gabriel.ecommerce.services.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    UserDto userDto = userService.create(userCreateDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
  }

  /**
   * Login.
   */
  @PostMapping("/auth/login")
  public ResponseEntity<Map<String, String>> login(
      @RequestBody LoginDto loginDto
  ) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);

    User user = (User) auth.getPrincipal();

    String token = tokenService.generateToken(user);

    Map<String, String> responseMap = new HashMap<>();
    responseMap.put("token", token);

    return ResponseEntity.status(HttpStatus.OK).body(responseMap);
  }
}
