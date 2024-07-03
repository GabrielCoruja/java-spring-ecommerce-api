package com.gabriel.ecommerce.user;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel.ecommerce.controller.dto.user.LoginDto;
import com.gabriel.ecommerce.controller.dto.user.UserCreateDto;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.repository.UserRepository;
import com.gabriel.ecommerce.security.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRepository userRepository;

  private String asJsonString(Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @DisplayName("POST /users create a new user")
  public void createSuccessTest() throws Exception {
    UserCreateDto userCreateDto = new UserCreateDto(
        "coruja",
        "password",
        "coruja@email.com",
        Role.ADMIN
    );
    User user = userCreateDto.toEntity();
    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    user.setId(1L);

    Mockito.when(userRepository.save(any())).thenReturn(user);

    mockMvc.perform(post("/users")
            .content(asJsonString(userCreateDto))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(user.getId()))
        .andExpect(jsonPath("$.username").value(user.getUsername()))
        .andExpect(jsonPath("$.role").value(user.getRole().toString()))
        .andExpect(jsonPath("$.email").doesNotExist())
        .andExpect(jsonPath("$.password").doesNotExist());

    Mockito.verify(userRepository).save(any());
  }

  @Nested
  @DisplayName("POST /auth/login login application")
  class loginApplicationTest {

    @Test
    @DisplayName("Login with valid data")
    public void loginSuccessTest() throws Exception {
      LoginDto loginDto = new LoginDto(
          "coruja",
          "password"
      );

      User user = new User(
          1L,
          "coruja",
          "coruja@email.com",
          "password",
          Role.ADMIN
      );
      user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

      Mockito.when(userRepository.findByUsername(any())).thenReturn(user);

      mockMvc.perform(post("/auth/login")
              .content(asJsonString(loginDto))
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.token").exists())
          .andExpect(jsonPath("$.token").isString());
    }

    @Test
    @DisplayName("Login with invalid data")
    public void loginFailedTest() throws Exception {
      LoginDto loginDto = new LoginDto(
          "wrong_username",
          "wrong_password"
      );

      Mockito.when(userRepository.findByUsername(any())).thenReturn(null);

      mockMvc.perform(post("/auth/login")
              .content(asJsonString(loginDto))
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isForbidden());
    }

  }
}
