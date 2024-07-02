package com.gabriel.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.ecommerce.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Class representing a user.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails, GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  @Column(unique = true)
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany
  private List<Sale> sales;

  /**
   * Instantiates a new User.
   */
  public User() {
  }

  /**
   * Instantiates a new User.
   *
   * @param username the username
   * @param email    the email
   * @param password the password
   * @param role     the role
   * @param sales    the sales
   */
  public User(String username, String email, String password, Role role, List<Sale> sales) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.sales = sales;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Sets username.
   *
   * @param username the username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email the email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Sets password.
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets role.
   *
   * @return the role
   */
  public Role getRole() {
    return role;
  }

  /**
   * Sets role.
   *
   * @param role the role
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /**
   * Gets sales.
   *
   * @return the sales
   */
  public List<Sale> getSales() {
    return sales;
  }

  /**
   * Sets sales.
   *
   * @param sales the sales
   */
  public void setSales(List<Sale> sales) {
    this.sales = sales;
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.toString()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  @JsonIgnore
  public String getAuthority() {
    return this.getRole().toString();
  }
}
