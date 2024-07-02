package com.gabriel.ecommerce.repository;

import com.gabriel.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Repository to operations of Users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Find by username user details.
   *
   * @param username the username
   * @return the user details
   */
  UserDetails findByUsername(String username);
}
