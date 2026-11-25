package com.gabriel.ecommerce.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gabriel.ecommerce.controller.dto.user.LoginDto;
import com.gabriel.ecommerce.controller.dto.user.UserCreateDto;
import com.gabriel.ecommerce.entity.User;
import com.gabriel.ecommerce.repository.UserRepository;
import com.gabriel.ecommerce.security.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserRepository userRepository;

  private String asJsonString(Object obj) {
    return JsonMapper.builder().build().writeValueAsString(obj);
  }

  /** Faz o repositório devolver o próprio usuário salvo, com id, para inspecionar o perfil. */
  private void mockSaveReturningArgument() {
    Mockito.when(userRepository.save(any())).thenAnswer(invocation -> {
      User saved = invocation.getArgument(0);
      saved.setId(1L);
      return saved;
    });
  }

  private Role savedRole() {
    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    Mockito.verify(userRepository).save(captor.capture());
    return captor.getValue().getRole();
  }

  @Nested
  @DisplayName("POST /users create a new user")
  class CreateUserTest {

    @Test
    @DisplayName("Cadastro público cria USER mesmo pedindo ADMIN")
    public void publicSignUpIgnoresRequestedRole() throws Exception {
      mockSaveReturningArgument();
      UserCreateDto dto = new UserCreateDto("coruja", "password", "coruja@email.com", Role.ADMIN);

      mockMvc.perform(post("/users")
              .content(asJsonString(dto))
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.id").value(1))
          .andExpect(jsonPath("$.username").value("coruja"))
          .andExpect(jsonPath("$.role").value("USER"))
          .andExpect(jsonPath("$.email").doesNotExist())
          .andExpect(jsonPath("$.password").doesNotExist());

      assertEquals(Role.USER, savedRole());
    }

    @Test
    @DisplayName("Cadastro público sem perfil cria USER")
    public void publicSignUpWithoutRoleCreatesUser() throws Exception {
      mockSaveReturningArgument();
      UserCreateDto dto = new UserCreateDto("coruja", "password", "coruja@email.com", null);

      mockMvc.perform(post("/users")
              .content(asJsonString(dto))
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.role").value("USER"));

      assertEquals(Role.USER, savedRole());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    @DisplayName("ADMIN autenticado pode escolher o perfil")
    public void adminCanChooseRole() throws Exception {
      mockSaveReturningArgument();
      UserCreateDto dto = new UserCreateDto("gerente", "password", "gerente@email.com",
          Role.MANAGER);

      mockMvc.perform(post("/users")
              .content(asJsonString(dto))
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.role").value("MANAGER"));

      assertEquals(Role.MANAGER, savedRole());
    }

    @Test
    @WithMockUser(authorities = "USER")
    @DisplayName("Usuário comum autenticado não consegue criar ADMIN")
    public void regularUserCannotCreateAdmin() throws Exception {
      mockSaveReturningArgument();
      UserCreateDto dto = new UserCreateDto("intruso", "password", "intruso@email.com",
          Role.ADMIN);

      mockMvc.perform(post("/users")
              .content(asJsonString(dto))
              .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.role").value("USER"));

      assertEquals(Role.USER, savedRole());
    }
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
