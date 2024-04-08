package com.gabriel.ecommerce.services;

import com.gabriel.ecommerce.dto.user.UserCreateDto;
import com.gabriel.ecommerce.dto.user.UserDto;
import com.gabriel.ecommerce.models.entities.User;
import com.gabriel.ecommerce.models.repositories.UserRepository;
import com.gabriel.ecommerce.utils.UserDtoConvert;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service interactive user data.
 */
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Creates a new user.
   */
  public UserDto create(UserCreateDto user) {
    String hashPassword = new BCryptPasswordEncoder().encode(user.password());

    User userResult = userRepository.save(UserDtoConvert.dtoToModel(user, hashPassword));

    return UserDtoConvert.modelToDto(userResult);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }
}
