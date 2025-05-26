package ru.lyudofa.srpringcourse.gymbro.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito; // Импорт для Mockito.mock
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration; // Импорт для TestConfiguration
import org.springframework.context.annotation.Bean; // Импорт для @Bean
import org.springframework.test.web.servlet.MockMvc;
import ru.lyudofa.srpringcourse.gymbro.controllers.UserController;
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.services.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class) // Укажите ваш класс контроллера
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Теперь мок UserService будет внедрен из TestConfig
    @Autowired
    private UserService userService;

    /**
     * Тест-специфичная конфигурация для создания мока UserService.
     * Этот мок будет использоваться в контексте Spring для данного теста,
     * и контроллер получит именно этот экземпляр мока.
     */
    @TestConfiguration
    static class ControllerTestConfig {
        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }
    }

    // Тесты остаются такими же, как в предыдущем ответе,
    // но теперь они используют userService, внедренный из TestConfig.

    @Test
    void showRegistrationForm_shouldReturnRegistrationView() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/register"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", org.hamcrest.Matchers.instanceOf(User.class)));
    }

    @Test
    void registerUser_shouldRegisterUserAndRedirectToLogin_whenDataIsValid() throws Exception {
        // Настраиваем мок userService, который теперь внедрен через @Autowired
        doNothing().when(this.userService).registerUser(any(User.class));

        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("email", "test@example.com")
                        .param("passwordHash", "password123") // Сырой пароль из формы
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login?registered"));

        verify(this.userService).registerUser(any(User.class));
    }

    @Test
    void registerUser_shouldReturnFormWithError_whenUsernameIsBlank() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "") // Невалидное значение
                        .param("email", "test@example.com")
                        .param("passwordHash", "password123")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("users/register"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("user", "username", "NotBlank"));

        verify(this.userService, never()).registerUser(any(User.class));
    }

    @Test
    void registerUser_shouldReturnFormWithError_whenEmailIsInvalid() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("email", "not-an-email") // Невалидное значение
                        .param("passwordHash", "password123")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("users/register"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("user", "email", "Email"));

        verify(this.userService, never()).registerUser(any(User.class));
    }

    @Test
    void registerUser_shouldReturnFormWithError_whenPasswordIsTooShort() throws Exception {
        // Предположим, что в User.java есть @Size(min=6) для passwordHash
        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("email", "test@example.com")
                        .param("passwordHash", "123") // Слишком короткий
                )
                .andExpect(status().isOk())
                .andExpect(view().name("users/register"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrorCode("user", "passwordHash", "Size"));

        verify(this.userService, never()).registerUser(any(User.class));
    }

    @Test
    void registerUser_shouldReturnFormWithErrorMessage_whenServiceThrowsException() throws Exception {
        String errorMessage = "User already exists";
        // Настраиваем мок userService
        doThrow(new RuntimeException(errorMessage)).when(this.userService).registerUser(any(User.class));

        mockMvc.perform(post("/register")
                        .param("username", "existinguser")
                        .param("email", "existing@example.com")
                        .param("passwordHash", "password123")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("users/register"))
                .andExpect(model().attribute("error", errorMessage));

        verify(this.userService).registerUser(any(User.class));
    }
}