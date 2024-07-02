package com.gabriel.ecommerce.service;

import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.exception.NotFoundException;
import com.gabriel.ecommerce.repository.UserRepository;
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

  /**
   * Instantiates a new User service.
   *
   * @param userRepository the user repository
   */
  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Creates a new user.
   *
   * @param user the user
   * @return the user
   */
  public User create(User user) {
    String hashPassword = new BCryptPasswordEncoder().encode(user.getPassword());
    user.setPassword(hashPassword);

    return userRepository.save(user);
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getUser(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException("User Not Found!"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }
}
